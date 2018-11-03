package de.schulte.wicketcompact;

import de.schulte.wicketcompact.entities.Category;
import de.schulte.wicketcompact.services.CategoryService;
import de.schulte.wicketcompact.services.ServiceRegistry;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import java.util.ArrayList;
import java.util.List;

public class CategoriesPage extends BaseEntitiesPage {

    public CategoriesPage(PageParameters parameters) {
        super(parameters);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        final List<Category> categoryList = new ArrayList<>(ServiceRegistry.get(CategoryService.class).listAll());
        final IDataProvider<Category> dataProvider = new ListDataProvider<>(categoryList);
        final DataView<Category> categories = new DataView<Category>("categories", dataProvider) {

            @Override
            protected void populateItem(Item<Category> item) {
                item.add(new Label("name", item.getModelObject().getName()));

                final AttributeAppender srcAppender = new AttributeAppender("src", item.getModelObject().getImageUrl());
                item.add(new WebMarkupContainer("image").add(srcAppender));
            }
        };
        categories.setItemsPerPage(3);
        final PagingNavigator navigator = new PagingNavigator("navigator", categories);
        add(categories);
        add(navigator);
    }
}
