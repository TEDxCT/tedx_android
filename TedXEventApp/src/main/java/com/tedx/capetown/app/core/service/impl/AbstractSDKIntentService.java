package com.tedx.capetown.app.core.service.impl;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tedx.capetown.app.core.converter.Converter;
import com.tedx.capetown.app.core.factory.sdk.impl.SDKClientFactoryImpl;
import com.tedx.capetown.app.core.service.SDKConnectorRequest;
import com.tedx.capetown.app.core.service.storage.StorageKey;
import com.tedx.capetown.app.core.service.storage.impl.StorageService;
import com.tedx.capetown.lib.sdk.SDKClient;
import com.tedx.capetown.lib.sdk.dto.DTO;
import com.tedx.capetown.lib.sdk.dto.SDKResponse;
import com.tedx.capetown.lib.sdk.exception.SDKException;

import java.io.IOException;
import java.text.ParseException;

/**
 * Created by andrewpettey on 2014/05/11.
 */
public abstract class AbstractSDKIntentService extends IntentService {

    private SDKClient sdkClient = null;
    private StorageService storage = null;

    protected SDKClient getSDKClient() {

        return sdkClient;
    }

    protected StorageService getStorage() {

        return storage;
    }

    public AbstractSDKIntentService(String name) {

        super(name);
    }

    protected <TSource extends DTO, TTarget> TTarget executeSdkConnectorRequest(SDKConnectorRequest<TSource> connectorRequest,
                                                                                Converter<TSource, TTarget> converter) throws IOException,
            IllegalArgumentException,
            IllegalAccessException, SDKException, ParseException {

        SDKResponse<TSource> response = connectorRequest.makeRequest();
        return converter.convert(response.responseDTO);
    }

    protected <T extends DTO> void cacheResponse(StorageKey key, T response) throws JsonProcessingException {

        try {
            getStorage().save(key, response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected <T extends DTO> T retrieveCachedResponse(StorageKey key, Class<T> responseType) throws IOException {

        return (T) getStorage().read(key);
    }

    public void initialise(Context ctx) {

        if (sdkClient == null) {
            sdkClient = SDKClientFactoryImpl.createSDKClient(ctx);
        }
        if (storage == null) {
            storage = new StorageService(ctx);
        }
    }

    @Override
    public final void onHandleIntent(Intent intent) {
        initialise(this);

        onHandleIntentAfterInitialization(intent);
    }

    protected abstract void onHandleIntentAfterInitialization(Intent intent);

    public void setSdkClient(SDKClient sdkClient) {

        this.sdkClient = sdkClient;
    }


    /**
     * @param storage the storage to set
     */
    public void setStorage(StorageService storage) {

        this.storage = storage;
    }
}

