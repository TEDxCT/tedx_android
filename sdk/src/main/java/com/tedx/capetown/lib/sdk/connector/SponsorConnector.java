package com.tedx.capetown.lib.sdk.connector;

import com.tedx.capetown.lib.sdk.connector.request.builder.SponsorRequestBuilder;
import com.tedx.capetown.lib.sdk.connector.request.impl.SponsorRequest;
import com.tedx.capetown.lib.sdk.dto.SDKResponse;
import com.tedx.capetown.lib.sdk.dto.SponsorCollectionDTO;
import com.tedx.capetown.lib.sdk.exception.SDKException;
import java.io.IOException;
import java.text.ParseException;

public interface SponsorConnector {
    SponsorRequestBuilder getSponsorRequestBuilder(String pathComponent);
    public SDKResponse<SponsorCollectionDTO> getSponsorList(SponsorRequest request) throws IOException, SDKException, ParseException;
}
