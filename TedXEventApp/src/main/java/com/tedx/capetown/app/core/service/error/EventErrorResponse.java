package com.tedx.capetown.app.core.service.error;

import com.tedx.capetown.app.core.service.request.EventCollectionServiceRequest;
import com.tedx.capetown.app.core.service.request.SpeakerCollectionServiceRequest;

/**
 * Created by andrewpettey on 2014/05/18.
 */
public class EventErrorResponse extends AbstractErrorResponse<EventCollectionServiceRequest> {
    public EventErrorResponse(EventCollectionServiceRequest eventCollectionServiceRequest, Exception e) {
        super(eventCollectionServiceRequest, e);
    }
}
