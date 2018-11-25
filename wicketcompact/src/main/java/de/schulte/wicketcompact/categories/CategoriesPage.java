package de.schulte.wicketcompact.categories;

import de.schulte.wicketcompact.BaseEntitiesPage;
import de.schulte.wicketcompact.EntityModel;
import de.schulte.wicketcompact.entities.Category;
import de.schulte.wicketcompact.services.CategoryService;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
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

    private final AjaxLink<Void> createCategory;
    private DataView<Category> categories;
    private final SortableDataProvider<Category, String> dataProvider;
    private WebMarkupContainer categoriesParent;
    private ModalWindow dialog;
    private EditCategory categoryEditPanel;

    public CategoriesPage(PageParameters parameters) {
        super(parameters);
        dataProvider = new CategoriesDataProvider();
        categoriesParent = new WebMarkupContainer("categoriesParent");
        add(categoriesParent);
        categories = new DataView<Category>("categories", dataProvider) {

            @Override
            protected void populateItem(Item<Category> item) {
                final Category category = item.getModelObject();
                item.add(new Label("name"));
                final AttributeAppender srcAppender = new AttributeAppender("src", new PropertyModel<>(new EntityModel<>(category, CategoryService.class), "imageUrl"));
                item.add(new WebMarkupContainer("image").add(srcAppender));
                item.add(new AjaxLink<Void>("modifyCategory") {
                    @Override
                    public void onClick(AjaxRequestTarget target) {
                        dialog.setTitle("Kategorie bearbeiten");
                        categoryEditPanel.setCategory(category);
                        dialog.show(target);
                    }
                });
            }
        };
        categories.setOutputMarkupPlaceholderTag(true);

        this.createCategory = new AjaxLink<Void>("createCategory") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                dialog.setTitle("Kategorie hinzufügen");
                categoryEditPanel.setCategory(new Category());
                dialog.show(target);
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
        categoriesParent.add(categories);
        categoriesParent.add(new OrderByBorder<>("orderByName", "name", this.dataProvider));
        categoriesParent.setOutputMarkupPlaceholderTag(true);

        this.dialog = new ModalWindow("dialog");
        this.categoryEditPanel = new EditCategory(this.dialog.getContentId());
        this.dialog.setContent(categoryEditPanel);
        add(dialog);

        dialog.setWindowClosedCallback(new ModalWindow.WindowClosedCallback() {
            @Override
            public void onClose(AjaxRequestTarget target) {
                target.add(categoriesParent);
            }
        });

        add(createCategory);
    }

}
