package de.schulte.wicketcompact;

import de.schulte.wicketcompact.entities.Category;
import de.schulte.wicketcompact.services.CategoryService;
import de.schulte.wicketcompact.services.ServiceRegistry;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.OrderByBorder;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.navigation.paging.IPageable;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.validation.validator.UrlValidator;

public class CategoriesPage extends BaseEntitiesPage {

    private final EntityModel<Category, CategoryService> formEntityModel = new EntityModel<>(CategoryService.class);
    private DataView<Category> categories;
    private final SortableDataProvider<Category, String> dataProvider;
    private final Form form = new Form<Category>("form") {

        @Override
        protected void onSubmit() {
            super.onSubmit();
            ServiceRegistry.get(CategoryService.class).save(this.getModelObject());
            form.setVisible(false);
        }
    };

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
        add(new Link<String>("newCategory") {
            @Override
            public void onClick() {
                form.setVisible(true);
                formEntityModel.setObject(new Category());
            }
        });
        initializeForm();
    }

    private void initializeForm() {
        add(new FeedbackPanel("feedback"));
        form.setModel(new CompoundPropertyModel<>(formEntityModel));
        add(form);
        form.add(new TextField<String>("name").setRequired(true));
        form.add(new TextField<String>("imageUrl").setRequired(true).add(new UrlValidator()));
        form.setVisible(false);
    }


}
