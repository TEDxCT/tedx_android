package com.tedx.capetown.app.core.service.request;

import android.content.Intent;

import com.tedx.capetown.app.core.service.params.EmptyParams;

/**
 * Created by andrewpettey on 2014/05/11.
 */
public class SpeakerCollectionServiceRequest extends AbstractServiceRequest<EmptyParams> {
    public SpeakerCollectionServiceRequest()
    {
        super();
    }
    public SpeakerCollectionServiceRequest(Intent intent) {

        super(intent);
    }
}
