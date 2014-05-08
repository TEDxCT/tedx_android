/**
 * 
 */
package com.tedx.capetown.app.core.service.storage;

import android.content.SharedPreferences;

/**
 * @author AndrewPettey
 * 
 */
public interface SharedPrefStorage extends Storage {

	public SharedPreferences getStorage();

	public SharedPreferences.Editor getEditor();

}
