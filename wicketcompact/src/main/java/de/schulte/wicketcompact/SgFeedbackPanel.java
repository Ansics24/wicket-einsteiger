package de.schulte.wicketcompact;

import de.schulte.wicketcompact.resources.FeedbackJsResourceReference;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

public class SgFeedbackPanel extends FeedbackPanel {

    public SgFeedbackPanel(String id) {
        super(id);
        setOutputMarkupPlaceholderTag(true);
    }

    @Override
    protected void onComponentTag(ComponentTag tag) {
        super.onComponentTag(tag);
        tag.put("class", "alert alert-danger");
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();
        setVisible(anyMessage());
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);
        response.render(JavaScriptHeaderItem.forReference(FeedbackJsResourceReference.get()));
        response.render(OnDomReadyHeaderItem.forScript(String.format("$('#%s').hideFeedback();", getMarkupId())));
    }
}
