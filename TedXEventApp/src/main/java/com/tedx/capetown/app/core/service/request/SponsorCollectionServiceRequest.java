package com.tedx.capetown.app.core.service.request;

import android.content.Intent;

import com.tedx.capetown.app.core.service.params.EmptyParams;

public class SponsorCollectionServiceRequest extends AbstractServiceRequest<EmptyParams> {

    public SponsorCollectionServiceRequest()
    {
        super();
    }

    public SponsorCollectionServiceRequest(Intent intent) {

        super(intent);
    }

}