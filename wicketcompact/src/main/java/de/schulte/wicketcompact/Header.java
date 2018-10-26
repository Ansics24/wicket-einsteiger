package de.schulte.wicketcompact;

import org.apache.wicket.markup.html.image.ContextImage;
import org.apache.wicket.markup.html.panel.Panel;

public class Header extends Panel {

    public Header(String id) {
        super(id);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(new PageLink("dashboard", getApplication().getHomePage()));
        add(new PageLink("categories", CategoriesPage.class));
        add(new PageLink("articles", ArticlesPage.class));
        add(new PageLink("tables", TablesPage.class));
        add(new ContextImage("brand", "sg-logo.png"));
    }
}

