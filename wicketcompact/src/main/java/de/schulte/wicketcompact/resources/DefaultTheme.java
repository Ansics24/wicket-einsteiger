package de.schulte.wicketcompact.resources;

import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.request.resource.PackageResourceReference;

import java.util.List;

public class DefaultTheme extends PackageResourceReference {

    private static final DefaultTheme INSTANCE = new DefaultTheme();

    public DefaultTheme() {
        super(DefaultTheme.class, "sg-default.css");
    }

    public static DefaultTheme get() {
        return INSTANCE;
    }

}
