package com.tedx.capetown.lib.sdk.connector.request.builder.impl;

import com.tedx.capetown.lib.sdk.connector.request.builder.EventRequestBuilder;
import com.tedx.capetown.lib.sdk.connector.request.impl.EventRequest;

import java.io.UnsupportedEncodingException;

/**
 * Created by andrewpettey on 2014/05/09.
 */
public class EventRequestBuilderImpl extends AbstractCollectionBasedRequestBuilder<EventRequest> implements EventRequestBuilder {
    private final String pathComponent;

    public EventRequestBuilderImpl(String pathComponent) {
        this.pathComponent = pathComponent;
    }

    @Override
    public EventRequest build() throws UnsupportedEncodingException {
        return new EventRequest(pathComponent);
    }
}
