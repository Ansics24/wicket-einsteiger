package de.schulte.wicketcompact;

import de.schulte.wicketcompact.resources.LogoResourceReference;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

public class Header extends Panel {

    public Header(String id) {
        super(id);
        add(new BookmarkablePageLink<Void>("dashboard", getApplication().getHomePage()));
        add(new BookmarkablePageLink<Void>("categories", CategoriesPage.class));
        add(new BookmarkablePageLink<Void>("articles", ArticlesPage.class));
        add(new BookmarkablePageLink<Void>("tables", TablesPage.class));
        add(new Image("brand", new LogoResourceReference()));
    }
}
