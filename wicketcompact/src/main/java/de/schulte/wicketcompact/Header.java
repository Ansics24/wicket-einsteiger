package de.schulte.wicketcompact;

import de.schulte.wicketcompact.entities.Article;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.image.ContextImage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.Panel;

public class Header extends Panel {

    private  MarkupContainer dashboardLink;
    private  MarkupContainer categoriesLink;
    private  MarkupContainer articlesLink;
    private  MarkupContainer tablesLink;

    public Header(String id) {
        super(id);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        dashboardLink = add(new BookmarkablePageLink<Void>("dashboard", getApplication().getHomePage()).setEnabled(!getPage().getClass().equals(HomePage.class)));
        categoriesLink = add(new BookmarkablePageLink<Void>("categories", CategoriesPage.class).setEnabled(!getPage().getClass().equals(CategoriesPage.class)));
        articlesLink = add(new BookmarkablePageLink<Void>("articles", ArticlesPage.class).setEnabled(!getPage().getClass().equals(ArticlesPage.class)));
        tablesLink = add(new BookmarkablePageLink<Void>("tables", TablesPage.class).setEnabled(!getPage().getClass().equals(TablesPage.class)));
        add(new ContextImage("brand", "sg-logo.png"));
    }
}

