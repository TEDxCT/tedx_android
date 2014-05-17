package com.tedx.capetown.app.core.service.request;

import android.content.Intent;

import com.tedx.capetown.app.core.service.params.EmptyParams;

public class SponsorModelServiceRequest extends AbstractServiceRequest<EmptyParams> {
    public SponsorModelServiceRequest() {

        super();
    }

    public SponsorModelServiceRequest(Intent intent) {

        super(intent);
    }
}
