package com.tedx.capetown.app.core.appsettings;

import android.os.Environment;

/**
 * Created by andrewpettey on 2014/05/11.
 */
public interface AppSettings {
    public String getSetting(AppSettingKey settingKey);

    public com.tedx.capetown.app.core.appsettings.impl.AppSettingsImpl.Environment getEnvironment();
}
