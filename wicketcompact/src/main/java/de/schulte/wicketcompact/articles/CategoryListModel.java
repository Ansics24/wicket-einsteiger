package de.schulte.wicketcompact.articles;

import de.schulte.wicketcompact.entities.Category;
import de.schulte.wicketcompact.services.CategoryService;
import de.schulte.wicketcompact.services.ServiceRegistry;
import org.apache.wicket.model.IModel;

import java.util.ArrayList;
import java.util.List;

public class CategoryListModel implements IModel<List<Category>> {
    @Override
    public List<Category> getObject() {
        return new ArrayList<>(ServiceRegistry.get(CategoryService.class).listAll());
    }
}
