package de.schulte.wicketcompact.services;

import de.schulte.wicketcompact.entities.BaseEntity;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseService<T extends BaseEntity> {

    private long entityIdCounter = 0;

    private Map<Long, T> entities = new HashMap<>();

    public T save(T entity) {
        if (entity.getId() == null) {
            return insert(entity);
        }
        return updateEntity(entity);
    }

    private T insert(T entity) {
        entity.setId(++entityIdCounter);
        entities.put(entity.getId(), entity);
        return entity;
    }

    private T updateEntity(T entity) {
        entities.put(entity.getId(), entity);
        return entity;
    }

    public T get(Long id) {
        return entities.get(id);
    }

    public Collection<T> listAll() {
        return entities.values();
    }

}
