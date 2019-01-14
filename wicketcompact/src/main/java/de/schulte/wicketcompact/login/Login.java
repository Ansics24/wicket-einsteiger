package de.schulte.wicketcompact.login;

import de.schulte.wicketcompact.HomePage;
import de.schulte.wicketcompact.SgSession;
import de.schulte.wicketcompact.resources.BootstrapCssResourceReference;
import de.schulte.wicketcompact.resources.DefaultTheme;
import org.apache.wicket.Session;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;

public class Login extends WebPage {

    private final TextField<String> username = new TextField("username", Model.of());
    private final PasswordTextField password = new PasswordTextField("password", Model.of());
    private final SubmitLink loginButton = new SubmitLink("login") {
        @Override
        public void onSubmit() {
            super.onSubmit();
            final String username = Login.this.username.getModelObject();
            if ("mustermann".equals(username) && password.getModelObject().equals("password")) {
                ((SgSession) Session.get()).loginUser(username);
                Login.this.setResponsePage(HomePage.class);
            }
        }
    };
    private Form<Void> form = new Form<>("form");

    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);
        response.render(CssHeaderItem.forReference(BootstrapCssResourceReference.get()));
        response.render(CssHeaderItem.forReference(DefaultTheme.get()));
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(form);
        form.add(username);
        form.add(password);
        form.add(loginButton);
    }
}
