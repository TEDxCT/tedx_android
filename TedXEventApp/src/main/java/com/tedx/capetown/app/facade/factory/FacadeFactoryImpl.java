package com.tedx.capetown.app.facade.factory;

import android.content.Context;

import com.tedx.capetown.app.core.factory.sdk.impl.SDKClientFactoryImpl;
import com.tedx.capetown.app.core.service.storage.impl.StorageService;
import com.tedx.capetown.app.facade.EventFacade;
import com.tedx.capetown.app.facade.FacadeFactory;
import com.tedx.capetown.app.facade.SpeakerFacade;
import com.tedx.capetown.app.facade.SponsorFacade;
import com.tedx.capetown.app.facade.impl.EventFacadeImpl;
import com.tedx.capetown.app.facade.impl.SpeakerFacadeImpl;
import com.tedx.capetown.app.facade.impl.SponsorFacadeImpl;
import com.tedx.capetown.app.facade.impl.StorageFacadeImpl;
import com.tedx.capetown.app.facade.storage.StorageFacade;

/**
 * Created by andrewpettey on 2014/05/12.
 */
public class FacadeFactoryImpl implements FacadeFactory {

    private final static String TAG = "FacadeFactory";

    public static EventFacade createEventFacade(Context context)
    {
        return new EventFacadeImpl(SDKClientFactoryImpl.createSDKClient(context), new StorageService());
    }

    public static SpeakerFacade createSpeakerFacade(Context context)
    {
        return new SpeakerFacadeImpl(SDKClientFactoryImpl.createSDKClient(context), new StorageService());
    }

    public static SponsorFacade createSponsorFacade(Context context)
    {
        return new SponsorFacadeImpl(SDKClientFactoryImpl.createSDKClient(context), new StorageService());
    }

    public static StorageFacade createStorageFacade()
    {
        return new StorageFacadeImpl();
    }
}