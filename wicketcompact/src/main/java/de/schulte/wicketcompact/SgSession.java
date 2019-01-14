package de.schulte.wicketcompact;

import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;

public class SgSession extends WebSession {

    private String userLoggedIn;

    /**
     * Constructor. Note that {@link RequestCycle} is not available until this constructor returns.
     *
     * @param request The current request
     */
    public SgSession(Request request) {
        super(request);
    }

    public void loginUser(final String username) {
        this.userLoggedIn = username;
    }

    public boolean isUserLoggedIn() {
        return userLoggedIn != null && !userLoggedIn.isEmpty();
    }

    public String getLoggedInUsername() {
        return this.userLoggedIn;
    }
}
