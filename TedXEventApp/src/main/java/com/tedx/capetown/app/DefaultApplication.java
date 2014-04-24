package com.tedx.capetown.app;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

import com.tedx.capetown.app.core.util.LogUtils;


/**
 * Created by andrewpettey on 2014/04/16.
 */
public class DefaultApplication extends Application {

    private static Context context;

    @Override
    public void onConfigurationChanged(Configuration newConfig) {

        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onCreate() {

        super.onCreate();
        LogUtils.LOGW("DefaultApplication", "onCreate()");
        DefaultApplication.context = getApplicationContext();
        initSingletons(DefaultApplication.context);
    }
    private void initSingletons(Context ctx) {

//        EventBus.getDefault().configureLogSubscriberExceptions(BuildSettings.DEBUG);

//        initHttpResponseCache(ctx);
        // Call to instantiate instance of appsettings linked to main Application context
//        AppSettingsFactoryImpl.create(ctx);
        // Instantiate Universal Image Loader library
//        initImageLoader(ctx);
//        SessionSettings.initInstance(ctx);

        // Initiate native CookieSyncManager
//        CookieSyncManager.createInstance(ctx);
    }
}
