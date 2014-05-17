package com.tedx.capetown.lib.sdk.connector.impl;

import com.tedx.capetown.lib.sdk.SDKClient;
import com.tedx.capetown.lib.sdk.connector.SponsorConnector;
import com.tedx.capetown.lib.sdk.connector.request.builder.SponsorRequestBuilder;
import com.tedx.capetown.lib.sdk.connector.request.builder.impl.SponsorRequestBuilderImpl;
import com.tedx.capetown.lib.sdk.connector.request.impl.SponsorRequest;
import com.tedx.capetown.lib.sdk.dto.SDKResponse;
import com.tedx.capetown.lib.sdk.dto.SponsorCollectionDTO;
import com.tedx.capetown.lib.sdk.exception.SDKException;

import java.io.IOException;
import java.text.ParseException;

public class SponsorConnectorImpl extends AbstractConnector implements SponsorConnector {
    public SponsorConnectorImpl(SDKClient client) {
        super(client);
    }

    @Override
    public SponsorRequestBuilder getSponsorRequestBuilder(String pathComponent) {
        return new SponsorRequestBuilderImpl(pathComponent);
    }

    @Override
    public SDKResponse<SponsorCollectionDTO> getSponsorList(SponsorRequest request) throws IOException, SDKException, ParseException {
        return sdkResponseForRequest(request, SponsorCollectionDTO.class);
    }
}