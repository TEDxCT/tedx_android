package com.tedx.capetown.app.core.service.error;

import com.tedx.capetown.app.core.service.request.SpeakerCollectionServiceRequest;

/**
 * Created by andrewpettey on 2014/05/18.
 */
public class SpeakerErrorResponse extends AbstractErrorResponse<SpeakerCollectionServiceRequest> {
    public SpeakerErrorResponse(SpeakerCollectionServiceRequest speakerCollectionServiceRequest, Exception e) {
        super(speakerCollectionServiceRequest, e);
    }
}
