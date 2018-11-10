package de.schulte.wicketcompact;

import com.google.gson.Gson;
import de.schulte.wicketcompact.entities.BaseEntity;
import de.schulte.wicketcompact.services.BaseService;
import de.schulte.wicketcompact.services.ServiceRegistry;
import org.apache.wicket.model.LoadableDetachableModel;

public class EntityModel<T extends BaseEntity, S extends BaseService<T>> extends LoadableDetachableModel<T> {

    private Long id;

    private Class<T> entityClass;

    private Class<S> serviceClass;

    private transient T entity;

    private String newObjectSerialized;

    public EntityModel(T object, Class<S> serviceClass) {
        this.entity = object;
        final Long id = this.entity.getId();
        if (id != null) {
            this.id = id;
        }
        this.serviceClass = serviceClass;
        this.entityClass = (Class<T>) object.getClass();
    }

    public EntityModel(Class<S> serviceClass) {
        this.serviceClass = serviceClass;
    }

    @Override
    public void setObject(T object) {
        super.setObject(object);
        this.entity = object;
        this.entityClass = (Class<T>) object.getClass();
    }

    @Override
    protected T load() {
        if (this.newObjectSerialized != null) {
            return new Gson().fromJson(this.newObjectSerialized, this.entityClass);
        }
        this.entity = ServiceRegistry.get(serviceClass).get(id);
        return this.entity;
    }

    @Override
    protected void onDetach() {
        super.onDetach();
        if (this.entity == null) {
            return;
        }
        if (this.entity.getId() == null) {
            this.newObjectSerialized = new Gson().toJson(this.entity);
        }
        this.entity = null;
    }
}
