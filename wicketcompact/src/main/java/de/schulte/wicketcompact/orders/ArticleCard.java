package de.schulte.wicketcompact.orders;

import de.schulte.wicketcompact.EntityModel;
import de.schulte.wicketcompact.LoadingIndicatingAjaxSubmitLink;
import de.schulte.wicketcompact.entities.Article;
import de.schulte.wicketcompact.entities.Order;
import de.schulte.wicketcompact.entities.Table;
import de.schulte.wicketcompact.services.ArticleService;
import de.schulte.wicketcompact.services.OrderService;
import de.schulte.wicketcompact.services.ServiceRegistry;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import java.math.BigDecimal;
import java.util.Arrays;

public class ArticleCard extends Panel {

    private final Label articleName = new Label("name");

    private final Label description = new Label("description");

    private final LoadingIndicatingAjaxSubmitLink singleOrder;

    private final WebMarkupContainer multiOrder = new WebMarkupContainer("multiOrder");

    private final WebMarkupContainer multiOrderDropdown = new WebMarkupContainer("multiOrderDropdown");

    private final WebMarkupContainer image = new WebMarkupContainer("image");

    private final IModel<Article> articleModel;

    private final ListView<Integer> multiOrderListView;

    private final Form orderForm = new Form("orderForm");

    private final WebMarkupContainer orderConfirmation;

    public ArticleCard(String id, final Article article) {
        super(id);
        this.articleModel = new EntityModel<>(article, ArticleService.class);
        setDefaultModel(this.articleModel);
        this.multiOrderListView = new ListView<Integer>("multiOrderListItem", Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8)) {
            @Override
            protected void populateItem(ListItem<Integer> item) {
                item.setRenderBodyOnly(true);
                final LoadingIndicatingAjaxSubmitLink multiOrderLink = new LoadingIndicatingAjaxSubmitLink("multiOrderLink") {

                    @Override
                    protected void onSubmit(AjaxRequestTarget target) {
                        super.onSubmit(target);
                        placeOrder(item.getModelObject(), target);
                    }
                };
                item.add(multiOrderLink);
                multiOrderLink.add(new Label("multiOrderQuantityLabel", item.getModelObject() + "x"));
                multiOrderLink.add(new Label("multiOrderPriceLabel", new Model<BigDecimal>() {
                    @Override
                    public BigDecimal getObject() {
                        return articleModel.getObject().getPrice().multiply(BigDecimal.valueOf(item.getModelObject()));
                    }
                }));
            }
        };
        this.singleOrder = new LoadingIndicatingAjaxSubmitLink("singleOrder") {

            @Override
            protected void onSubmit(AjaxRequestTarget target) {
                super.onSubmit(target);
                placeOrder(1, target);
            }
        };
        this.orderConfirmation = new WebMarkupContainer("orderConfirmation");
        this.orderConfirmation.setOutputMarkupPlaceholderTag(true);
    }

    private void placeOrder(Integer quantity, AjaxRequestTarget target) {
        ServiceRegistry.get(OrderService.class).save(new Order(getTable(), this.articleModel.getObject(), quantity));
        this.orderConfirmation.setVisible(true);
        target.add(this.orderConfirmation);
        target.appendJavaScript(String.format("$('#%s').show(); window.setTimeout(()=>{" +
                "    $('#%s').hide(600);" +
                "}, 3000);", this.orderConfirmation.getMarkupId(), this.orderConfirmation.getMarkupId()));
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(articleName);
        add(image);
        image.add(new AttributeModifier("src", new Model<String>() {

            @Override
            public String getObject() {
                return articleModel.getObject().getImageUrl();
            }
        }));
        add(description);

        add(orderForm);
        orderForm.add(singleOrder);
        singleOrder.add(new Label("singleOrderLabel", articleModel.getObject().getPrice()));
        orderForm.add(multiOrder);
        multiOrderDropdown.add(new AttributeModifier("aria-labelledby", multiOrder.getMarkupId()));
        orderForm.add(multiOrderDropdown);
        multiOrderDropdown.add(multiOrderListView);

        add(orderConfirmation);
        orderConfirmation.setVisible(false);
    }

    private Table getTable() {
        return findParent(Menu.class).getTable();
    }

}
