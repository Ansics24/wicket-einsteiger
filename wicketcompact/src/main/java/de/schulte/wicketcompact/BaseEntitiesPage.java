package de.schulte.wicketcompact;

import org.apache.wicket.markup.html.navigation.paging.IPageable;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public abstract class BaseEntitiesPage extends BaseWebPage {

    public BaseEntitiesPage(PageParameters parameters) {
        super(parameters);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(new PagingPanel("navigator", getPageable()));

    }

    protected abstract IPageable getPageable();
}
