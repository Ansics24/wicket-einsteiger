package de.schulte.wicketcompact;

import org.apache.wicket.feedback.ExactLevelFeedbackMessageFilter;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

public class SuccessFeedbackPanel extends FeedbackPanel {

    public SuccessFeedbackPanel(String id) {
        super(id, new ExactLevelFeedbackMessageFilter(FeedbackMessage.SUCCESS));
    }
}
