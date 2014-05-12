package com.tedx.capetown.lib.sdk.connector.impl;

import com.tedx.capetown.lib.sdk.SDKClient;
import com.tedx.capetown.lib.sdk.connector.EventConnector;
import com.tedx.capetown.lib.sdk.connector.request.builder.EventRequestBuilder;
import com.tedx.capetown.lib.sdk.connector.request.builder.impl.EventRequestBuilderImpl;
import com.tedx.capetown.lib.sdk.connector.request.impl.EventRequest;
import com.tedx.capetown.lib.sdk.dto.EventCollectionDTO;
import com.tedx.capetown.lib.sdk.dto.SDKResponse;
import com.tedx.capetown.lib.sdk.exception.SDKException;

import java.io.IOException;
import java.text.ParseException;

/**
 * Created by andrewpettey on 2014/05/09.
 */
public class EventConnectorImpl extends AbstractConnector implements EventConnector {
    protected EventConnectorImpl(SDKClient client) {
        super(client);
    }

    @Override
    public EventRequestBuilder getEventRequestBuilder(String pathComponent) {
        return new EventRequestBuilderImpl(pathComponent);
    }

    @Override
    public SDKResponse<EventCollectionDTO> getEventInformation(EventRequest request) throws IOException, SDKException, ParseException {
        return sdkResponseForRequest(request,EventCollectionDTO.class);
    }
}
