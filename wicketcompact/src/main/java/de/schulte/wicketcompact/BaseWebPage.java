package de.schulte.wicketcompact;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public abstract class BaseWebPage extends WebPage {

    public BaseWebPage(PageParameters parameters) {
        super(parameters);
        add(new Header("header"));
        add(new Footer("footer"));
    }


}
