package de.schulte.wicketcompact.resources;

import org.apache.wicket.request.resource.PackageResourceReference;

public class FeedbackJsResourceReference extends PackageResourceReference {

    private static final FeedbackJsResourceReference INSTANCE = new FeedbackJsResourceReference();

    public FeedbackJsResourceReference() {
        super(FeedbackJsResourceReference.class, "feedback.js");
    }

    public static FeedbackJsResourceReference get() {
        return INSTANCE;
    }
}
