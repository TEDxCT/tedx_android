/**
 * 
 */
package com.tedx.capetown.app.core.service.storage.impl;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.tedx.capetown.app.core.service.storage.SharedPrefStorage;


/**
 * @author Johan
 * 
 */
public class SharedPrefStorageImpl implements SharedPrefStorage {

	private static final String PREFS_NAME = "APP_PREFS";
	private final Context context;
	private SharedPreferences sharedPreferences;
	private Editor editor;

	public SharedPrefStorageImpl(Context context) {

		this.context = context;
	}

	@Override
	public boolean save(String key, String value) {

		return getEditor().putString(key, value).commit();
	}

	@Override
	public Object read(String key) {

		Object o = getStorage().getString(key, "");
		return o;
	}

	@Override
	public void remove(String key) {

		if (getStorage().contains(key)) {
			getEditor().remove(key).commit();
		}
	}

	@Override
	public SharedPreferences getStorage() {

		if (sharedPreferences == null) {
			sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
		}
		return sharedPreferences;
	}

	@Override
	public Editor getEditor() {

		if (editor == null) {
			editor = getStorage().edit();
		}
		return editor;
	}

}
