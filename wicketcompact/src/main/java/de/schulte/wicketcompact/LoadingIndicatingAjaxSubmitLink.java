package de.schulte.wicketcompact;

import org.apache.wicket.ajax.IAjaxIndicatorAware;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.extensions.ajax.markup.html.AjaxIndicatorAppender;

public class LoadingIndicatingAjaxSubmitLink extends AjaxSubmitLink implements IAjaxIndicatorAware {

    private final AjaxIndicatorAppender ajaxIndicatorAppender;

    public LoadingIndicatingAjaxSubmitLink(String id) {
        super(id);
        this.ajaxIndicatorAppender = new AjaxIndicatorAppender();
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(this.ajaxIndicatorAppender);
    }

    @Override
    public String getAjaxIndicatorMarkupId() {
        return this.ajaxIndicatorAppender.getMarkupId();
    }
}
