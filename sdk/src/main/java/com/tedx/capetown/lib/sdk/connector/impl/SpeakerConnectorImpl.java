package com.tedx.capetown.lib.sdk.connector.impl;

import com.tedx.capetown.lib.sdk.SDKClient;
import com.tedx.capetown.lib.sdk.connector.SpeakerConnector;
import com.tedx.capetown.lib.sdk.connector.request.builder.SpeakerRequestBuilder;
import com.tedx.capetown.lib.sdk.connector.request.builder.impl.SpeakerRequestBuilderImpl;
import com.tedx.capetown.lib.sdk.connector.request.impl.SpeakerRequest;
import com.tedx.capetown.lib.sdk.dto.SDKResponse;
import com.tedx.capetown.lib.sdk.dto.SpeakerCollectionDTO;
import com.tedx.capetown.lib.sdk.exception.SDKException;

import java.io.IOException;
import java.text.ParseException;

/**
 * Created by andrewpettey on 2014/05/09.
 */
public class SpeakerConnectorImpl extends AbstractConnector implements SpeakerConnector {
    public SpeakerConnectorImpl(SDKClient client) {
        super(client);
    }

    @Override
    public SpeakerRequestBuilder getSpeakerRequestBuilder(String pathComponent) {
        return new SpeakerRequestBuilderImpl(pathComponent);
    }

    @Override
    public SDKResponse<SpeakerCollectionDTO> getSpeakerList(SpeakerRequest request) throws IOException, SDKException, ParseException {
        return sdkResponseForRequest(request, SpeakerCollectionDTO.class);
    }
}