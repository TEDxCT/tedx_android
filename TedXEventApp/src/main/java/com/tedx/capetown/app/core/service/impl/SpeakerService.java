package com.tedx.capetown.app.core.service.impl;

import android.app.Service;
import android.content.Intent;
import android.util.Log;

import com.tedx.capetown.app.core.converter.impl.SpeakerCollectionConverter;
import com.tedx.capetown.app.core.models.SpeakerCollectionModel;
import com.tedx.capetown.app.core.service.SDKConnectorRequest;
import com.tedx.capetown.app.core.service.request.SpeakerCollectionServiceRequest;
import com.tedx.capetown.app.core.service.request.SpeakerModelServiceRequest;
import com.tedx.capetown.lib.sdk.connector.SpeakerConnector;
import com.tedx.capetown.lib.sdk.connector.request.impl.SpeakerRequest;
import com.tedx.capetown.lib.sdk.dto.SDKResponse;
import com.tedx.capetown.lib.sdk.dto.SpeakerCollectionDTO;
import com.tedx.capetown.lib.sdk.exception.SDKException;

import java.io.IOException;
import java.text.ParseException;

/**
 * Created by andrewpettey on 2014/05/11.
 */
public class SpeakerService extends AbstractSDKIntentService {

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
                Log.wtf("TEST","Request created");
                SDKResponse<SpeakerCollectionDTO> response = speakerConnector.getSpeakerList(request);
                Log.wtf("TEST","Request sent:"+response.responseDTO.toString());
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
            Log.wtf("TEST","TEst:"+action.equals(SpeakerCollectionServiceRequest.class.getName()));
            try {
                fetchSpeakerList();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SDKException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        else if (action.equals(SpeakerModelServiceRequest.class.getName()))
        {
            Log.wtf("TEST","SpeakerModelServiceRequest: "+action);
        }
        Log.wtf("TEST","action"+action);
        }
}
