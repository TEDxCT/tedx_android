package com.tedx.capetown.lib.sdk.connector;

import com.tedx.capetown.lib.sdk.connector.request.builder.SpeakerRequestBuilder;
import com.tedx.capetown.lib.sdk.connector.request.impl.SpeakerRequest;
import com.tedx.capetown.lib.sdk.dto.SDKResponse;
import com.tedx.capetown.lib.sdk.dto.SpeakerCollectionDTO;
import com.tedx.capetown.lib.sdk.exception.SDKException;

import java.io.IOException;
import java.text.ParseException;

/**
 * Created by andrewpettey on 2014/05/11.
 */
public interface SpeakerConnector {

    SpeakerRequestBuilder getSpeakerRequestBuilder(String pathComponent);

    public SDKResponse<SpeakerCollectionDTO> getSpeakerList(SpeakerRequest request) throws IOException,
            SDKException, ParseException;
}
