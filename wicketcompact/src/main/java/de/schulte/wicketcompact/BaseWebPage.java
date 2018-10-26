package de.schulte.wicketcompact;

import de.schulte.wicketcompact.resources.BootstrapCssResourceReference;
import de.schulte.wicketcompact.resources.DefaultTheme;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public abstract class BaseWebPage extends WebPage {

    public BaseWebPage(PageParameters parameters) {
        super(parameters);
        add(new Header("header").setRenderBodyOnly(true));
        add(new Footer("footer").setRenderBodyOnly(true));
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);
        response.render(CssHeaderItem.forReference(BootstrapCssResourceReference.get()));
        response.render(CssHeaderItem.forReference(DefaultTheme.get()));
    }
}
