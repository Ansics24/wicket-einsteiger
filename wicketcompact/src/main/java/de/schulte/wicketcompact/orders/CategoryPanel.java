package de.schulte.wicketcompact.orders;

import de.schulte.wicketcompact.articles.ArticlesByCategoryDataProvider;
import de.schulte.wicketcompact.entities.Article;
import de.schulte.wicketcompact.entities.Category;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;

public class CategoryPanel extends Panel {

    private final Label categoryName = new Label("name");

    private final DataView<Article> articlesDataView;

    public CategoryPanel(String id, final Category category) {
        super(id);
        articlesDataView = new DataView<Article>("articles", new ArticlesByCategoryDataProvider(category)) {
            @Override
            protected void populateItem(Item<Article> item) {
                item.add(new ArticleCard("article", item.getModelObject()));
                item.setRenderBodyOnly(true);
            }
        };
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(categoryName);
        add(this.articlesDataView);
    }

}
