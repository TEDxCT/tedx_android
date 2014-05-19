package com.tedx.capetown.app;

import android.app.Application;
import android.content.Context;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.tedx.capetown.app.core.appsettings.factory.impl.AppSettingsFactoryImpl;
import com.tedx.capetown.lib.sdk.BuildSettings;

import de.greenrobot.event.EventBus;

/**
 * Created by andrewpettey on 2014/05/12.
 */
public class DefaultApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {

        super.onCreate();
        DefaultApplication.context = getApplicationContext();
        initSingletons(DefaultApplication.context);
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext()).build();
        ImageLoader.getInstance().init(config);
    }
    private void initSingletons(Context ctx) {

        EventBus.getDefault().configureLogSubscriberExceptions(BuildSettings.DEBUG);

        AppSettingsFactoryImpl.create(ctx);
    }

    @Override
    public void onLowMemory() {

        super.onLowMemory();
    }

    // NOTE: This will only be called by the emulator
    @Override
    public void onTerminate() {

        super.onTerminate();
    }

    public static Context getAppContext() {

        return DefaultApplication.context;
    }
}
