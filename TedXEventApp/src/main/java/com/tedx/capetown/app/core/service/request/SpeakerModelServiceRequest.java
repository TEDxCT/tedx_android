package com.tedx.capetown.app.core.service.request;

import android.content.Intent;

import com.tedx.capetown.app.core.service.params.EmptyParams;

/**
 * Created by andrewpettey on 2014/05/11.
 */
public class SpeakerModelServiceRequest extends AbstractServiceRequest<EmptyParams> {
    public SpeakerModelServiceRequest() {

        super();
    }

    public SpeakerModelServiceRequest(Intent intent) {

        super(intent);
    }
}
