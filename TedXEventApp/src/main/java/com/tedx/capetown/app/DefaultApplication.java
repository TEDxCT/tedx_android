package com.tedx.capetown.app;

import android.app.Application;
import android.content.Context;

import com.google.android.gms.maps.MapsInitializer;
import com.nostra13.universalimageloader.cache.disc.DiscCacheAware;
import com.nostra13.universalimageloader.cache.disc.impl.LimitedAgeDiscCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.tedx.capetown.app.core.appsettings.factory.impl.AppSettingsFactoryImpl;
import com.tedx.capetown.app.core.factory.imageloader.impl.ImageLoaderFactoryImpl;
import com.tedx.capetown.lib.sdk.BuildSettings;

import de.greenrobot.event.EventBus;

public class DefaultApplication extends Application
{
    private static Context context;

    @Override
    public void onCreate()
    {
        super.onCreate();
        DefaultApplication.context = getApplicationContext();
        initSingletons(DefaultApplication.context);

        DisplayImageOptions options = new ImageLoaderFactoryImpl().createDisplayImageOptions(DefaultApplication.context).build();

        // Disc Cache 7 days
        DiscCacheAware discCache = new LimitedAgeDiscCache(StorageUtils.getCacheDirectory(context), 604800);

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .discCache(discCache)
                .defaultDisplayImageOptions(
                        options).build();
        ImageLoader.getInstance().init(config);
        MapsInitializer.initialize(this);
    }

    private void initSingletons(Context ctx)
    {
        EventBus.getDefault().configureLogSubscriberExceptions(BuildSettings.DEBUG);
        AppSettingsFactoryImpl.create(ctx);
    }

    @Override
    public void onLowMemory()
    {
        super.onLowMemory();
    }

    // NOTE: This will only be called by the emulator
    @Override
    public void onTerminate()
    {
        super.onTerminate();
    }

    public static Context getAppContext()
    {
        return DefaultApplication.context;
    }

}
