package com.tedx.capetown.app.core.service.impl;

import android.app.Service;
import android.content.Intent;
import android.util.Log;

import com.tedx.capetown.app.core.converter.Converter;
import com.tedx.capetown.app.core.converter.impl.SpeakerCollectionConverter;
import com.tedx.capetown.app.core.models.SpeakerCollectionModel;
import com.tedx.capetown.app.core.service.SDKConnectorRequest;
import com.tedx.capetown.app.core.service.error.SpeakerErrorResponse;
import com.tedx.capetown.app.core.service.request.SpeakerCollectionServiceRequest;
import com.tedx.capetown.app.core.service.request.SpeakerModelServiceRequest;
import com.tedx.capetown.lib.sdk.connector.SpeakerConnector;
import com.tedx.capetown.lib.sdk.connector.request.impl.SpeakerRequest;
import com.tedx.capetown.lib.sdk.dto.DTO;
import com.tedx.capetown.lib.sdk.dto.ErrorDTO;
import com.tedx.capetown.lib.sdk.dto.SDKResponse;
import com.tedx.capetown.lib.sdk.dto.SpeakerCollectionDTO;
import com.tedx.capetown.lib.sdk.exception.SDKClientException;
import com.tedx.capetown.lib.sdk.exception.SDKException;

import java.io.IOException;
import java.text.ParseException;

import de.greenrobot.event.EventBus;

/**
 * Created by andrewpettey on 2014/05/11.
 */
public class SpeakerService extends AbstractSDKIntentService {
    private static String TAG = "Srv:Speaker";
    public SpeakerService() {

        super(ServiceIdentifier.Speaker.name());
    }

    private SpeakerCollectionModel fetchSpeakerList() throws IllegalArgumentException,
            IllegalAccessException,
            IOException,
            SDKException, ParseException {

        return executeSdkConnectorRequest(new SDKConnectorRequest<SpeakerCollectionDTO>() {

            @Override
            public SDKResponse<SpeakerCollectionDTO> makeRequest() throws IOException,
                    SDKException,
                    ParseException {
                SpeakerConnector speakerConnector = getSDKClient().getSpeakerConnector();
                SpeakerRequest request = speakerConnector.getSpeakerRequestBuilder("tedx_server/response/speakers.php").build();
                SDKResponse<SpeakerCollectionDTO> response = speakerConnector.getSpeakerList(request);
                return response;
            }
        }, new SpeakerCollectionConverter(SpeakerCollectionDTO.class,SpeakerCollectionModel.class));

    }
    @Override
    protected void onHandleIntentAfterInitialization(Intent intent) {
        String action = intent.getAction();
        if (action == null) {
            return;
        }
        if (action.equals(SpeakerCollectionServiceRequest.class.getName())) {
            try {
                SpeakerCollectionModel speakerCollectionModel = fetchSpeakerList();
                EventBus.getDefault().postSticky(speakerCollectionModel);
            } catch (Exception e) {
                SpeakerErrorResponse speakerErrorResponse = new SpeakerErrorResponse(null, e);
                EventBus.getDefault().postSticky(speakerErrorResponse);

            }
        }
        else if (action.equals(SpeakerModelServiceRequest.class.getName()))
        {
            Log.wtf(TAG,"SpeakerModelServiceRequest: "+action);
        }
    }
}
