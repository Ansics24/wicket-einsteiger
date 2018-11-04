package de.schulte.wicketcompact;

import de.schulte.wicketcompact.entities.Article;
import de.schulte.wicketcompact.services.ArticleService;
import de.schulte.wicketcompact.services.ServiceRegistry;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.navigation.paging.IPageable;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import java.util.ArrayList;

public class ArticlesPage extends BaseEntitiesPage {

    private final DataView<Article> articles;

    public ArticlesPage(PageParameters parameters) {
        super(parameters);
        IDataProvider<Article> dataProvider = new ListDataProvider<>(new ArrayList<>(ServiceRegistry.get(ArticleService.class).listAll()));
        this.articles = new DataView<Article>("articles", dataProvider) {

            @Override
            protected void populateItem(Item<Article> item) {
                final Article article = item.getModelObject();
                item.add(new Label("name", article.getName()));
                item.add(new Label("category", article.getCategory().getName()));
                item.add(new Label("description", article.getDescription()));
                item.add(new Label("price", article.getPrice()));
                item.add(new Label("validFrom", article.getValidFrom()));
                item.add(new Label("validTo", article.getValidTo()));
                final AttributeAppender srcAppender = new AttributeAppender("src", item.getModelObject().getImageUrl());
                item.add(new WebMarkupContainer("image").add(srcAppender));
            }
        };
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(this.articles);
    }

    @Override
    protected IPageable getPageable() {
        return this.articles;
    }
}
