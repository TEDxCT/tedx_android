package com.tedx.capetown.app.core.service;

import com.tedx.capetown.lib.sdk.dto.DTO;
import com.tedx.capetown.lib.sdk.dto.SDKResponse;
import com.tedx.capetown.lib.sdk.exception.SDKException;

import java.io.IOException;
import java.text.ParseException;

/**
 * Created by andrewpettey on 2014/05/11.
 */

public interface SDKConnectorRequest<T extends DTO> {

    public SDKResponse<T> makeRequest() throws IOException,
            SDKException,
            ParseException;
}