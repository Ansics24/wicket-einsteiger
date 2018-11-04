package de.schulte.wicketcompact;

import de.schulte.wicketcompact.entities.Category;
import de.schulte.wicketcompact.services.CategoryService;
import de.schulte.wicketcompact.services.ServiceRegistry;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.navigation.paging.IPageable;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigation;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import java.util.ArrayList;
import java.util.List;

public class CategoriesPage extends BaseEntitiesPage {

    private DataView<Category> categories;

    public CategoriesPage(PageParameters parameters) {
        super(parameters);
        final List<Category> categoryList = new ArrayList<>(ServiceRegistry.get(CategoryService.class).listAll());
        final IDataProvider<Category> dataProvider = new ListDataProvider<>(categoryList);
        categories = new DataView<Category>("categories", dataProvider) {

            @Override
            protected void populateItem(Item<Category> item) {
                item.add(new Label("name", item.getModelObject().getName()));

                final AttributeAppender srcAppender = new AttributeAppender("src", item.getModelObject().getImageUrl());
                item.add(new WebMarkupContainer("image").add(srcAppender));
            }
        };
    }

    @Override
    protected IPageable getPageable() {
        return categories;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        categories.setItemsPerPage(3);
        add(categories);
    }


}
