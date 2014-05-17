package com.tedx.capetown.lib.sdk.connector.request.builder.impl;

import com.tedx.capetown.lib.sdk.connector.request.builder.SponsorRequestBuilder;
import com.tedx.capetown.lib.sdk.connector.request.impl.SponsorRequest;

import java.io.UnsupportedEncodingException;

public class SponsorRequestBuilderImpl extends AbstractCollectionBasedRequestBuilder<SponsorRequest> implements SponsorRequestBuilder {
    private final String pathComponent;

    public SponsorRequestBuilderImpl(String pathComponent) {
        this.pathComponent = pathComponent;
    }

    @Override
    public SponsorRequest build() throws UnsupportedEncodingException {
        return new SponsorRequest(pathComponent);
    }
}
