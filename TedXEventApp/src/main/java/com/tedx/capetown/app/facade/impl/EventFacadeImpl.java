package com.tedx.capetown.app.facade.impl;

import com.tedx.capetown.app.DefaultApplication;
import com.tedx.capetown.app.core.service.request.EventCollectionServiceRequest;
import com.tedx.capetown.app.core.service.storage.impl.StorageService;
import com.tedx.capetown.app.facade.EventFacade;
import com.tedx.capetown.lib.sdk.SDKClient;

/**
 * Created by andrewpettey on 2014/05/12.
 */
public class EventFacadeImpl extends AbstractFacade implements EventFacade {

    public EventFacadeImpl(SDKClient sdkClient, StorageService storage) {

        super(sdkClient, storage);
    }
    @Override
    public void fetchEventList() {
        EventCollectionServiceRequest request = new EventCollectionServiceRequest();
        DefaultApplication.getAppContext().startService(request.createIntent());
    }
}
