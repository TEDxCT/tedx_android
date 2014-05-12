package com.tedx.capetown.app.facade.impl;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.tedx.capetown.app.core.converter.Converter;
import com.tedx.capetown.app.core.service.storage.StorageKey;
import com.tedx.capetown.app.core.service.storage.impl.StorageService;
import com.tedx.capetown.app.facade.ConnectorRequest;
import com.tedx.capetown.lib.sdk.SDKClient;
import com.tedx.capetown.lib.sdk.dto.DTO;
import com.tedx.capetown.lib.sdk.dto.SDKResponse;
import com.tedx.capetown.lib.sdk.exception.SDKException;

import java.io.IOException;
import java.text.ParseException;

/**
 * Created by andrewpettey on 2014/05/12.
 */
public abstract class AbstractFacade {

    private final SDKClient sdkClient;
    private final StorageService storage;

    public AbstractFacade(SDKClient sdkClient, StorageService storage) {

        this.sdkClient = sdkClient;
        this.storage = storage;
    }

    protected SDKClient getSDKClient() {

        return sdkClient;
    }

    protected StorageService getStorage() {

        return storage;
    }

    protected <TSource extends DTO, TTarget> TTarget executeConnectorRequest(ConnectorRequest<TSource> connectorRequest,
                                                                             Converter<TSource, TTarget> converter) throws IOException,
            IllegalArgumentException,
            IllegalAccessException, SDKException, ParseException {

        SDKResponse<TSource> response = connectorRequest
                .makeRequest();
        return converter.convert(response.responseDTO);
    }

    protected <T extends DTO> void cacheResponse(StorageKey key, T response) throws JsonProcessingException {

        try {
            getStorage().save(key, response);
        } catch (IOException e) {
            // ANDO Auto-generated catch block
            e.printStackTrace();
        }
    }

    protected <T extends DTO> T retrieveCachedResponse(StorageKey key, Class<T> responseType) throws JsonParseException,
            JsonMappingException,
            IOException {

        return (T) getStorage().read(key);
    }
}
