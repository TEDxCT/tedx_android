package com.tedx.capetown.app.core.service.request;

import android.content.Intent;

import com.tedx.capetown.app.core.service.params.EmptyParams;

/**
 * Created by andrewpettey on 2014/05/11.
 */
public class EventModelServiceRequest extends AbstractServiceRequest<EmptyParams> {
    public EventModelServiceRequest() {

        super();
    }

    public EventModelServiceRequest(Intent intent) {

        super(intent);
    }
}
