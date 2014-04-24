/**
 * 
 */
package com.tedx.capetown.app.core.util;

import android.util.Log;
import com.tedx.capetown.app.BuildSettings;


/**
 * @author Andrew Pettey
 * 
 */
public class LogUtils {

	public static void LOGD(final String tag, String message) {

		if (BuildSettings.DEBUG) {
			Log.d(tag, message);
		}
	}

	public static void LOGD(final String tag, String message, Throwable cause) {

		if (BuildSettings.DEBUG) {
			Log.d(tag, message, cause);
		}
	}

	public static void LOGV(final String tag, String message) {

		if (BuildSettings.DEBUG) {
			Log.v(tag, message);
		}
	}

	public static void LOGV(final String tag, String message, Throwable cause) {

		if (BuildSettings.DEBUG) {
			Log.v(tag, message, cause);
		}
	}

	public static void LOGI(final String tag, String message) {

		if (BuildSettings.DEBUG) {
			Log.i(tag, message);
		}
	}

	public static void LOGI(final String tag, String message, Throwable cause) {

		if (BuildSettings.DEBUG) {
			Log.i(tag, message, cause);
		}
	}

	public static void LOGW(final String tag, String message) {

		if (BuildSettings.DEBUG) {
			Log.w(tag, message);
		}
	}

	public static void LOGW(final String tag, String message, Throwable cause) {

		if (BuildSettings.DEBUG) {
			Log.w(tag, message, cause);
		}
	}

	public static void LOGE(final String tag, String message) {

		if (BuildSettings.DEBUG) {
			Log.e(tag, message);
		}
	}

	public static void LOGE(final String tag, String message, Throwable cause) {

		if (BuildSettings.DEBUG) {
			Log.e(tag, message, cause);
		}
	}

	public static void LOGWTF(final String tag, String message) {

		if (BuildSettings.DEBUG) {
			Log.wtf(tag, message);
		}
	}

	public static void LOGWTF(final String tag, Throwable cause) {

		if (BuildSettings.DEBUG) {
			Log.wtf(tag, cause);
		}
	}

	public static void LOGWTF(final String tag, String message, Throwable cause) {

		if (BuildSettings.DEBUG) {
			Log.wtf(tag, message, cause);
		}
	}
}
