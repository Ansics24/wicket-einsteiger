package de.schulte.wicketcompact;

import de.schulte.wicketcompact.entities.Category;
import de.schulte.wicketcompact.services.CategoryService;
import de.schulte.wicketcompact.services.ServiceRegistry;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CategoriesDataProvider extends SortableDataProvider<Category, String> {

    public CategoriesDataProvider() {
        setSort("name", SortOrder.ASCENDING);
    }

    @Override
    public Iterator<? extends Category> iterator(long first, long count) {
        final List<Category> allCategories = new ArrayList<>(ServiceRegistry.get(CategoryService.class).listAll());

        final SortParam<String> sortOrder = getSort();
        allCategories.sort((c1, c2) -> {
            final String name1 = c1.getName();
            final String name2 = c2.getName();
            return sortOrder.isAscending() ? name1.compareTo(name2) : name2.compareTo(name1);
        });

        final List<Category> subList = allCategories.subList((int) first, (int) (first + count >= allCategories.size() ? allCategories.size() : first + count));
        return subList.iterator();
    }

    @Override
    public long size() {
        return ServiceRegistry.get(CategoryService.class).listAll().size();
    }

    @Override
    public IModel<Category> model(Category category) {
        return new EntityModel<>(category.getId(), CategoryService.class);
    }

}
