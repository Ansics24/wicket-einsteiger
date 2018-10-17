package de.schulte.wicketcompact.services;

import java.util.HashMap;
import java.util.Map;

public class ServiceRegistry {

    private static final Map<Class<? extends BaseService>, BaseService> SERVICES = new HashMap<>();

    private ServiceRegistry() {
    }

    public static <T extends BaseService> T get(Class<T> serviceClass) {
        BaseService service = SERVICES.get(serviceClass);
        if (service == null) {
            try {
                service = serviceClass.newInstance();
                SERVICES.put(serviceClass, service);
            } catch (InstantiationException | IllegalAccessException e) {
                throw new RuntimeException("Service konnte nicht erstellt werden", e);
            }
        }
        return (T) service;
    }

}
