package de.schulte.wicketcompact;

import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

public class SgFeedbackPanel extends FeedbackPanel {

    public SgFeedbackPanel(String id) {
        super(id);
        setOutputMarkupPlaceholderTag(true);
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);
        response.render(OnDomReadyHeaderItem.forScript("window.alert('Feedbackpanel rendered');"));
    }
}
