package de.schulte.wicketcompact.orders;

import de.schulte.wicketcompact.EntityModel;
import de.schulte.wicketcompact.entities.Article;
import de.schulte.wicketcompact.services.ArticleService;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public class ArticleCard extends Panel {

    private final Label articleName = new Label("name");

    private final Label description = new Label("description");

    private final Label price = new Label("price");

    private final WebMarkupContainer image = new WebMarkupContainer("image");

    private final IModel<Article> articleModel;

    public ArticleCard(String id, final Article article) {
        super(id);
        this.articleModel = new EntityModel<>(article, ArticleService.class);
        setDefaultModel(this.articleModel);
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
        add(price);
    }
}
