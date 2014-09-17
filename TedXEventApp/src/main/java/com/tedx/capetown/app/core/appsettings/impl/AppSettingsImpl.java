package com.tedx.capetown.app.core.appsettings.impl;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.util.Log;

import com.tedx.capetown.app.core.appsettings.AppSettingKey;
import com.tedx.capetown.app.core.appsettings.AppSettings;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by andrewpettey on 2014/05/11.
 */
public class AppSettingsImpl implements AppSettings {
    private Environment _environment;

    private Properties _defaultProperties;
    private Properties _environmentOverideProperties;

    public enum Environment {

        DEV("development"), PP("live");

        private String environment;

        private Environment(String env) {

            environment = env;
        }

        public static Environment getEnvironment(String env) {

            Environment environment = Environment.DEV;
            for (Environment e : values()) {
                if (e.toString().equals(env)) {
                    environment = e;
                    break;
                }
            }
            return environment;
        }

        @Override
        public String toString() {

            return environment;
        }

    }

    public AppSettingsImpl(Context context) {

        if (_defaultProperties == null || _environmentOverideProperties == null)
        {
            initializeProperties(context);
        }

    }

    private void initializeProperties(Context context) {

        try {
            Resources resources = context.getResources();
            AssetManager assetManager = resources.getAssets();
            InputStream inputStream;

            _defaultProperties = new Properties();

            inputStream = assetManager.open("appsettings/appsettings.properties");
            _defaultProperties.load(inputStream);
            inputStream.close();

            // get the target environment
            String env = _defaultProperties.getProperty(AppSettingKey.TargetEnvironment.toString());
            _environment = Environment.getEnvironment(env);

            // load the appropriate environment file
            _environmentOverideProperties = new Properties();

            inputStream = assetManager.open("appsettings/appsettings-" + _environment.toString() + ".properties");
            _environmentOverideProperties.load(inputStream);
            inputStream.close();

        } catch (IOException e) {
        }

    }

    @Override
    public String getSetting(AppSettingKey settingKey) {

        String result = null;
        result = _environmentOverideProperties.getProperty(settingKey.toString());

        if (result == null) {
            result = _defaultProperties.getProperty(settingKey.toString());
        }

        return result;
    }

    @Override
    public Environment getEnvironment() {

        return _environment;
    }

}
