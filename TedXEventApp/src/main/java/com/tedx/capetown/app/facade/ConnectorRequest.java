package com.tedx.capetown.app.facade;

import com.tedx.capetown.lib.sdk.dto.DTO;
import com.tedx.capetown.lib.sdk.dto.SDKResponse;
import com.tedx.capetown.lib.sdk.exception.SDKException;

import java.io.IOException;
import java.text.ParseException;

/**
 * Created by andrewpettey on 2014/05/12.
 */
public interface ConnectorRequest<T extends DTO> {

    public SDKResponse<T> makeRequest() throws IOException,
            SDKException,
            ParseException;
}

