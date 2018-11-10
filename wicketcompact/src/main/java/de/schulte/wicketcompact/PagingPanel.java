package de.schulte.wicketcompact;

import org.apache.wicket.markup.html.navigation.paging.IPageable;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigation;
import org.apache.wicket.markup.html.panel.Panel;

public class PagingPanel extends Panel {

    private final IPageable pageable;

    public PagingPanel(String id, IPageable pageable) {
        super(id);
        this.pageable = pageable;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        final PagingNavigation navigator = new PagingNavigation("navigator", pageable);
        add(navigator);
    }
}
