package com.tedx.capetown.app.core.service.impl;

import android.content.Intent;
import android.util.Log;

import com.tedx.capetown.app.core.converter.impl.EventCollectionConverter;
import com.tedx.capetown.app.core.models.EventCollectionModel;
import com.tedx.capetown.app.core.models.EventCollectionModel;
import com.tedx.capetown.app.core.service.SDKConnectorRequest;
import com.tedx.capetown.app.core.service.error.EventErrorResponse;
import com.tedx.capetown.app.core.service.error.SpeakerErrorResponse;
import com.tedx.capetown.app.core.service.request.EventCollectionServiceRequest;
import com.tedx.capetown.app.core.service.request.EventModelServiceRequest;
import com.tedx.capetown.lib.sdk.connector.EventConnector;
import com.tedx.capetown.lib.sdk.connector.request.impl.EventRequest;
import com.tedx.capetown.lib.sdk.dto.SDKResponse;
import com.tedx.capetown.lib.sdk.dto.EventCollectionDTO;
import com.tedx.capetown.lib.sdk.exception.SDKException;

import java.io.IOException;
import java.text.ParseException;

import de.greenrobot.event.EventBus;

/**
 * Created by andrewpettey on 2014/05/11.
 */
public class EventService extends AbstractSDKIntentService {

    public EventService() {

        super(ServiceIdentifier.Event.name());
    }

    private EventCollectionModel fetchEventList() throws IllegalArgumentException,
            IllegalAccessException,
            IOException,
            SDKException, ParseException {

        return executeSdkConnectorRequest(new SDKConnectorRequest<EventCollectionDTO>() {

            @Override
            public SDKResponse<EventCollectionDTO> makeRequest() throws IOException,
                    SDKException,
                    ParseException {

                EventConnector eventConnector = getSDKClient().getEventConnector();
                EventRequest request = eventConnector.getEventRequestBuilder("tedx_server/response/event.php").build();
                SDKResponse<EventCollectionDTO> response = eventConnector.getEventList(request);
                return response;
            }
        }, new EventCollectionConverter(EventCollectionDTO.class,EventCollectionModel.class));

    }
    @Override
    protected void onHandleIntentAfterInitialization(Intent intent) {
        String action = intent.getAction();
        if (action == null) {
            return;
        }
        if (action.equals(EventCollectionServiceRequest.class.getName())) {
            try {
                EventCollectionModel eventCollectionModel = fetchEventList();
                EventBus.getDefault().postSticky(eventCollectionModel);
            } catch (Exception e) {
                EventErrorResponse eventErrorResponse = new EventErrorResponse(null, e);
                EventBus.getDefault().postSticky(eventErrorResponse);
            }
        }
        else if (action.equals(EventModelServiceRequest.class.getName()))
        {
        }
    }
}
