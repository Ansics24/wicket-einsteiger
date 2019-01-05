package de.schulte.wicketcompact.orders;

import de.schulte.wicketcompact.entities.Table;
import de.schulte.wicketcompact.resources.BootstrapCssResourceReference;
import de.schulte.wicketcompact.services.ServiceRegistry;
import de.schulte.wicketcompact.services.TableService;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;

public class Menu extends WebPage {

    private final WebMarkupContainer tableNotSupportingOrderingHint;

    public Menu() {
        this.tableNotSupportingOrderingHint = new WebMarkupContainer("tableNotSupportingOrderingHint");
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        final long tableId = getPageParameters().get("tableId").toLong();
        final Table table = ServiceRegistry.get(TableService.class).get(tableId);
        add(new Label("tableName", table.getName()));
        add(this.tableNotSupportingOrderingHint);
        tableNotSupportingOrderingHint.setVisible(!table.getOrderableElectronically());
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);
        response.render(CssHeaderItem.forReference(BootstrapCssResourceReference.get()));
    }
}
