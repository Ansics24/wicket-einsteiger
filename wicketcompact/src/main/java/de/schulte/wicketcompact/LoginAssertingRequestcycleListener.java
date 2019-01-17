package de.schulte.wicketcompact;

import de.schulte.wicketcompact.login.Login;
import org.apache.wicket.Session;
import org.apache.wicket.request.cycle.IRequestCycleListener;
import org.apache.wicket.request.cycle.RequestCycle;

public class LoginAssertingRequestcycleListener implements IRequestCycleListener {

    @Override
    public void onBeginRequest(RequestCycle cycle) {
        if (cycle.getRequest().getClientUrl().getPath().endsWith("login")) {
            return;
        }
        final SgSession session = (SgSession) Session.get();
        if (!session.isUserLoggedIn()) {
            cycle.setResponsePage(Login.class);
        }
    }
}
