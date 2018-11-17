package de.schulte.wicketcompact.articles;

import de.schulte.wicketcompact.BaseWebPage;
import de.schulte.wicketcompact.entities.Article;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class CreateArticlePage extends BaseWebPage {

    public CreateArticlePage(PageParameters parameters) {
        super(parameters);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(new EditArticle("editArticle").setArticle(new Article()));
    }

}
