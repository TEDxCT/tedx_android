package com.tedx.capetown.app.facade.impl;

import android.util.Log;

import com.tedx.capetown.app.DefaultApplication;
import com.tedx.capetown.app.core.service.request.SpeakerCollectionServiceRequest;
import com.tedx.capetown.app.core.service.storage.impl.StorageService;
import com.tedx.capetown.app.facade.SpeakerFacade;
import com.tedx.capetown.lib.sdk.SDKClient;

/**
 * Created by andrewpettey on 2014/05/12.
 */
public class SpeakerFacadeImpl extends AbstractFacade implements SpeakerFacade{

    public SpeakerFacadeImpl(SDKClient sdkClient, StorageService storage) {

        super(sdkClient, storage);
    }
    @Override
    public void fetchSpeakerList() {
        SpeakerCollectionServiceRequest request = new SpeakerCollectionServiceRequest();
        DefaultApplication.getAppContext().startService(request.createIntent());
    }
}
