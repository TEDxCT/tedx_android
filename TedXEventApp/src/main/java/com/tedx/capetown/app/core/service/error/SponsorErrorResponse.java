package com.tedx.capetown.app.core.service.error;

import com.tedx.capetown.app.core.service.request.SpeakerCollectionServiceRequest;
import com.tedx.capetown.app.core.service.request.SponsorCollectionServiceRequest;

/**
 * Created by andrewpettey on 2014/05/18.
 */
public class SponsorErrorResponse extends AbstractErrorResponse<SponsorCollectionServiceRequest> {
    public SponsorErrorResponse(SponsorCollectionServiceRequest sponsorCollectionServiceRequest, Exception e) {
        super(sponsorCollectionServiceRequest, e);
    }
}
