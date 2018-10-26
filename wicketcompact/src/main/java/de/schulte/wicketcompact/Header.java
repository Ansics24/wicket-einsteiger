package de.schulte.wicketcompact;

import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.image.ContextImage;
import org.apache.wicket.markup.html.panel.Panel;

public class Header extends Panel {

    private WebMarkupContainer navbar;

    public Header(String id) {
        super(id);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        navbar = new WebMarkupContainer("navbar");
        navbar.add(new PageLink("dashboard", getApplication().getHomePage()));
        navbar.add(new PageLink("categories", CategoriesPage.class));
        navbar.add(new PageLink("articles", ArticlesPage.class));
        navbar.add(new PageLink("tables", TablesPage.class));
        final Tenant tenant = Tenant.get();
        navbar.add(new ContextImage("brand", tenant.equals(Tenant.DEFAULT) ? "sg-logo.png" : "cafeone-logo.png"));
        navbar.add(new AttributeAppender("class", tenant.equals(Tenant.DEFAULT) ? " navbar-dark bg-dark" : " navbar-light bg-light"));
        add(navbar);
    }
}

