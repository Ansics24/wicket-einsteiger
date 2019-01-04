package de.schulte.wicketcompact.resources;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.request.resource.PackageResourceReference;

import java.util.List;

public class JQueryUiJsResourceReference extends PackageResourceReference {

    private static final JQueryUiJsResourceReference INSTANCE = new JQueryUiJsResourceReference();

    public JQueryUiJsResourceReference() {
        super(JQueryUiJsResourceReference.class, "jquery-ui.min.js");
    }

    public static JQueryUiJsResourceReference get() {
        return INSTANCE;
    }

    @Override
    public List<HeaderItem> getDependencies() {
        final List<HeaderItem> dependencies = super.getDependencies();
        dependencies.add(CssHeaderItem.forReference(JQueryUiCssResourceReference.get()));
        return dependencies;
    }
}
