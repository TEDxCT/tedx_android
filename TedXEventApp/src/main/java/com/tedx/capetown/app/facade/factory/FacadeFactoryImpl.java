package com.tedx.capetown.app.facade.factory;

import android.content.Context;

import com.tedx.capetown.app.core.factory.sdk.impl.SDKClientFactoryImpl;
import com.tedx.capetown.app.core.service.storage.impl.StorageService;
import com.tedx.capetown.app.facade.FacadeFactory;
import com.tedx.capetown.app.facade.SpeakerFacade;
import com.tedx.capetown.app.facade.impl.SpeakerFacadeImpl;

/**
 * Created by andrewpettey on 2014/05/12.
 */
public class FacadeFactoryImpl implements FacadeFactory {

    private final static String TAG = "FacadeFactory";

    public static SpeakerFacade createSpeakerFacade(Context context) {

        return new SpeakerFacadeImpl(SDKClientFactoryImpl.createSDKClient(context), new StorageService(context));
    }
}