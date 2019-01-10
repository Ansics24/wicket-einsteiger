package de.schulte.wicketcompact.orders;

import de.schulte.wicketcompact.EntityModel;
import de.schulte.wicketcompact.categories.CategoriesDataProvider;
import de.schulte.wicketcompact.entities.Category;
import de.schulte.wicketcompact.entities.Table;
import de.schulte.wicketcompact.resources.BootstrapCssResourceReference;
import de.schulte.wicketcompact.resources.DefaultTheme;
import de.schulte.wicketcompact.services.ServiceRegistry;
import de.schulte.wicketcompact.services.TableService;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.IModel;

public class Menu extends WebPage {

    private final WebMarkupContainer tableNotSupportingOrderingHint;

    private final DataView<Category> categoryDataView;

    private IModel<Table> tableModel = new EntityModel<Table, TableService>(TableService.class);

    public Menu() {
        this.tableNotSupportingOrderingHint = new WebMarkupContainer("tableNotSupportingOrderingHint");
        this.categoryDataView = new DataView<Category>("categories", new CategoriesDataProvider()) {

            @Override
            protected void populateItem(Item<Category> item) {
                item.add(new CategoryPanel("categoryPanel", item.getModelObject()));
                item.setRenderBodyOnly(true);
            }

        };
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        final long tableId = getPageParameters().get("tableId").toLong();
        tableModel.setObject(ServiceRegistry.get(TableService.class).get(tableId));
        add(new Label("tableName", tableModel.getObject().getName()));
        add(this.tableNotSupportingOrderingHint);
        tableNotSupportingOrderingHint.setVisible(!tableModel.getObject().getOrderableElectronically());
        add(categoryDataView);
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);
        response.render(CssHeaderItem.forReference(BootstrapCssResourceReference.get()));
        response.render(CssHeaderItem.forReference(DefaultTheme.get()));
    }

    public Table getTable() {
        return this.tableModel.getObject();
    }

    @Override
    protected void onDetach() {
        super.onDetach();
        this.tableModel.detach();
    }
}
