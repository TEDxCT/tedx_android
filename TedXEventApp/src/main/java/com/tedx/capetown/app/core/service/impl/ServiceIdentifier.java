package com.tedx.capetown.app.core.service.impl;

/**
 * Created by andrewpettey on 2014/05/11.
 */
public enum ServiceIdentifier {
    Speaker("SpeakerService",SpeakerService.class);

    private final String serviceKey;
    private final Class serviceClass;
    private ServiceIdentifier() {

        serviceKey = name();
        serviceClass = String.class;
    }
    private <T> ServiceIdentifier(String storageKey) {

        this(storageKey, String.class);
    }

    private <T> ServiceIdentifier(String serviceKey, Class<T> serviceClass) {

        this.serviceKey = serviceKey;
        this.serviceClass = serviceClass;
    }

    public String getStorageKey(String seedValue) {

        return String.format(serviceKey, seedValue);
    }

    public String getServiceKey() {

        return serviceKey;
    }

    public Class getServiceClass() {

        return serviceClass;
    }

}
