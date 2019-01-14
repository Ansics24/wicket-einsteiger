package de.schulte.wicketcompact;

import de.schulte.wicketcompact.resources.BootstrapCssResourceReference;
import de.schulte.wicketcompact.resources.CafeoneTheme;
import de.schulte.wicketcompact.resources.DefaultTheme;
import org.apache.wicket.Session;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class BaseWebPage extends WebPage {

    private Tenant tenant;

    public BaseWebPage(PageParameters parameters) {
        super(parameters);
        add(new Header("header").setRenderBodyOnly(true));
        add(new Footer("footer").setRenderBodyOnly(true));
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        this.tenant = Tenant.get();
        try {
            final SgSession session = (SgSession) Session.get();
            if (!session.isUserLoggedIn()) {
                ((HttpServletResponse) RequestCycle.get().getResponse().getContainerResponse()).sendRedirect("/login");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);
        response.render(CssHeaderItem.forReference(BootstrapCssResourceReference.get()));
        if (tenant.equals(Tenant.DEFAULT)) {
            response.render(CssHeaderItem.forReference(DefaultTheme.get()));
        } else {
            response.render(CssHeaderItem.forReference(CafeoneTheme.get()));
        }
    }
}
