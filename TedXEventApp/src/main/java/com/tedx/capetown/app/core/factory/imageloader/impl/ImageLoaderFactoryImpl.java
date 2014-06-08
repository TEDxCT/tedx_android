package com.tedx.capetown.app.core.factory.imageloader.impl;

import android.content.Context;
import android.graphics.Bitmap;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.DisplayImageOptions.Builder;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.tedx.capetown.app.R;

import java.io.File;

/**
 * Created by andrewpettey on 2014/06/08.
 */
public class ImageLoaderFactoryImpl {
    public static Builder   createDisplayImageOptions(Context context){


        Builder builder = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.drawable.ic_drawer)
                .showImageOnFail(R.drawable.ic_launcher)
                .showStubImage(R.drawable.splash)
                .cacheInMemory(true)
                .cacheOnDisc(false)
                .bitmapConfig(Bitmap.Config.RGB_565);
        return builder;
    }
}
