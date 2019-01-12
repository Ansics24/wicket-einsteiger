package de.schulte.wicketcompact;

import de.schulte.wicketcompact.entities.Order;
import de.schulte.wicketcompact.entities.OrderStatus;
import de.schulte.wicketcompact.orders.OrdersDataProvider;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AbstractAjaxTimerBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.extensions.markup.html.repeater.tree.NestedTree;
import org.apache.wicket.extensions.markup.html.repeater.tree.content.Folder;
import org.apache.wicket.extensions.markup.html.repeater.tree.theme.WindowsTheme;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.time.Duration;

import javax.swing.tree.DefaultMutableTreeNode;

public class HomePage extends BaseWebPage {

    private static final long serialVersionUID = 1L;

    private final NestedTree<DefaultMutableTreeNode> dashboardTree;

    private final DataView<Order> orders;

    private final WebMarkupContainer ordersParent;

    public HomePage(final PageParameters parameters) {
        super(parameters);
        this.dashboardTree = new NestedTree<DefaultMutableTreeNode>("dashboardTree", new DashboardTreeProvider()) {
            @Override
            protected Component newContentComponent(String id, IModel<DefaultMutableTreeNode> model) {
                return new Folder<>(id, this, model);
            }
        };
        this.dashboardTree.add(new WindowsTheme());

        this.ordersParent = new WebMarkupContainer("ordersParent");
        this.orders = new DataView<Order>("orders", new OrdersDataProvider()) {
            @Override
            protected void populateItem(Item<Order> item) {
                item.add(new Label("quantity"));
                item.add(new Label("article.name"));
                item.add(new Label("table.name"));
                item.add(new Label("creationTime"));
                final Label statusLabel = new Label("status.text");
                item.add(statusLabel);
                statusLabel.add(new AttributeAppender("class", () -> {
                    final OrderStatus status = item.getModelObject().getStatus();
                    switch (status) {
                        case NEW:
                            return " badge-danger";
                        case PREPARATION:
                            return " badge-warning";
                        case DONE:
                            return " badge-success";
                        default:
                            return "";
                    }
                }));
            }
        };
        this.ordersParent.setOutputMarkupPlaceholderTag(true);
        this.dashboardTree.setOutputMarkupPlaceholderTag(true);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(this.dashboardTree);
        add(ordersParent);
        ordersParent.add(this.orders);

        add(new AbstractAjaxTimerBehavior(Duration.seconds(5)) {
            @Override
            protected void onTimer(AjaxRequestTarget target) {
                target.add(ordersParent);
                target.add(dashboardTree);
            }
        });
    }
}
