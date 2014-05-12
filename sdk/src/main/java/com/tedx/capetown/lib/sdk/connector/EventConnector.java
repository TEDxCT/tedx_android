package com.tedx.capetown.lib.sdk.connector;

import com.tedx.capetown.lib.sdk.connector.request.builder.EventRequestBuilder;
import com.tedx.capetown.lib.sdk.connector.request.impl.EventRequest;
import com.tedx.capetown.lib.sdk.dto.EventCollectionDTO;
import com.tedx.capetown.lib.sdk.dto.SDKResponse;
import com.tedx.capetown.lib.sdk.exception.SDKException;

import java.io.IOException;
import java.text.ParseException;

/**
 * Created by andrewpettey on 2014/05/09.
 */
public interface EventConnector {

    EventRequestBuilder getEventRequestBuilder(String pathComponent);

    public SDKResponse<EventCollectionDTO> getEventList(EventRequest request) throws IOException,
            SDKException, ParseException;
}
