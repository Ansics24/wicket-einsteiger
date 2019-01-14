package de.schulte.wicketcompact;

import org.apache.wicket.Session;
import org.apache.wicket.request.cycle.IRequestCycleListener;
import org.apache.wicket.request.cycle.RequestCycle;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginAssertingRequestcycleListener implements IRequestCycleListener {

    @Override
    public void onBeginRequest(RequestCycle cycle) {
        if (cycle.getRequest().getClientUrl().getPath().endsWith("login")) {
            return;
        }
        try {
            final SgSession session = (SgSession) Session.get();
            if (!session.isUserLoggedIn()) {
                ((HttpServletResponse) cycle.getResponse().getContainerResponse()).sendRedirect("/login");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
