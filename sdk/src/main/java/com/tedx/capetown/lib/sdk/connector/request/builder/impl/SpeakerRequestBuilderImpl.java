package com.tedx.capetown.lib.sdk.connector.request.builder.impl;

import com.tedx.capetown.lib.sdk.connector.request.builder.SpeakerRequestBuilder;
import com.tedx.capetown.lib.sdk.connector.request.impl.SpeakerRequest;

import java.io.UnsupportedEncodingException;

/**
 * Created by andrewpettey on 2014/05/09.
 */
public class SpeakerRequestBuilderImpl extends AbstractCollectionBasedRequestBuilder<SpeakerRequest> implements SpeakerRequestBuilder {
    private final String pathComponent;

    public SpeakerRequestBuilderImpl(String pathComponent) {
        this.pathComponent = pathComponent;
    }

    @Override
    public SpeakerRequest build() throws UnsupportedEncodingException {
        return new SpeakerRequest(pathComponent);
    }
}
