package de.schulte.wicketcompact.resources;

import org.apache.wicket.request.resource.PackageResourceReference;

public class CafeoneTheme extends PackageResourceReference {

    private static final CafeoneTheme INSTANCE = new CafeoneTheme();

    public CafeoneTheme() {
        super(CafeoneTheme.class, "cafeone.css");
    }

    public static CafeoneTheme get() {
        return INSTANCE;
    }

}
