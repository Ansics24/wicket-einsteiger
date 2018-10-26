package de.schulte.wicketcompact;

public enum Tenant {

    DEFAULT,
    CAFEONE;

    public static Tenant get() {
        final String tenant = System.getProperty("tenant");
        if(tenant == null) {
            return Tenant.DEFAULT;
        }
        return Tenant.valueOf(tenant.toUpperCase());
    }

}
