package de.schulte.wicketcompact.resources;

import org.apache.wicket.request.resource.PackageResourceReference;

public class JQueryUiCssResourceReference extends PackageResourceReference {

    private static final JQueryUiCssResourceReference INSTANCE = new JQueryUiCssResourceReference();

    public JQueryUiCssResourceReference() {
        super(JQueryUiCssResourceReference.class, "jquery-ui.min.css");
    }

    public static JQueryUiCssResourceReference get() {
        return INSTANCE;
    }

}
