package com.tedx.capetown.app.facade.impl;

import com.tedx.capetown.app.DefaultApplication;
import com.tedx.capetown.app.core.service.request.SpeakerCollectionServiceRequest;
import com.tedx.capetown.app.core.service.request.SponsorCollectionServiceRequest;
import com.tedx.capetown.app.core.service.storage.impl.StorageService;
import com.tedx.capetown.app.facade.SpeakerFacade;
import com.tedx.capetown.app.facade.SponsorFacade;
import com.tedx.capetown.lib.sdk.SDKClient;

public class SponsorFacadeImpl extends AbstractFacade implements SponsorFacade {

    public SponsorFacadeImpl(SDKClient sdkClient, StorageService storage) {
        super(sdkClient, storage);
    }

    @Override
    public void fetchSponsorList() {
        SponsorCollectionServiceRequest request = new SponsorCollectionServiceRequest();
        DefaultApplication.getAppContext().startService(request.createIntent());
    }

}
