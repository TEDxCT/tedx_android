package com.tedx.capetown.app.core.appsettings.factory.impl;

import android.content.Context;

import com.tedx.capetown.app.core.appsettings.AppSettings;
import com.tedx.capetown.app.core.appsettings.factory.AppSettingsFactory;
import com.tedx.capetown.app.core.appsettings.impl.AppSettingsImpl;

/**
 * Created by andrewpettey on 2014/05/11.
 */
public class AppSettingsFactoryImpl implements AppSettingsFactory {
    private static AppSettings instance;

    public static AppSettings create(Context context) {

        if (instance == null) {

            instance = new AppSettingsImpl(context);
        }
        return instance;
    }
}
