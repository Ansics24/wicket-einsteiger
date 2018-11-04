package de.schulte.wicketcompact;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.navigation.paging.IPageable;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigation;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public abstract class BaseEntitiesPage extends BaseWebPage {

    public BaseEntitiesPage(PageParameters parameters) {
        super(parameters);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        final PagingNavigation navigator = new PagingNavigation("navigator", getPageable());
        add(navigator);
    }

    protected abstract IPageable getPageable();
}
