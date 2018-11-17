package de.schulte.wicketcompact.categories;

import de.schulte.wicketcompact.BaseEntitiesPage;
import de.schulte.wicketcompact.EntityModel;
import de.schulte.wicketcompact.entities.Category;
import de.schulte.wicketcompact.services.CategoryService;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.OrderByBorder;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.navigation.paging.IPageable;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class CategoriesPage extends BaseEntitiesPage {

    private DataView<Category> categories;
    private final SortableDataProvider<Category, String> dataProvider;

    public CategoriesPage(PageParameters parameters) {
        super(parameters);
        dataProvider = new CategoriesDataProvider();
        categories = new DataView<Category>("categories", dataProvider) {

            @Override
            protected void populateItem(Item<Category> item) {
                final Category category = item.getModelObject();
                item.add(new Label("name"));
                final AttributeAppender srcAppender = new AttributeAppender("src", new PropertyModel<>(new EntityModel<>(category, CategoryService.class), "imageUrl"));
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
        add(new OrderByBorder<>("orderByName", "name", this.dataProvider));
    }

}
