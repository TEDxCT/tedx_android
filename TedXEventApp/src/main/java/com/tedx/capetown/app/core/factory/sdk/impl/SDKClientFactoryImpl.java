package com.tedx.capetown.app.core.factory.sdk.impl;

import android.content.Context;

import com.tedx.capetown.app.core.appsettings.AppSettingKey;
import com.tedx.capetown.app.core.appsettings.factory.impl.AppSettingsFactoryImpl;
import com.tedx.capetown.app.core.factory.sdk.SDKClientFactory;
import com.tedx.capetown.lib.sdk.SDKClient;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by andrewpettey on 2014/05/11.
 */
public class SDKClientFactoryImpl implements SDKClientFactory{
    public static SDKClient createSDKClient(Context context){

        SDKClient client = new SDKClient();
        // Buggy right now - need to resolve
        String apiRoot = "http://95.85.26.105/";
        try {
            client.setAPIRoot(URI.create(apiRoot));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return client;
    }
}
