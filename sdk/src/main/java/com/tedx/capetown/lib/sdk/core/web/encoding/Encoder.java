/**
 * 
 */
package com.tedx.capetown.lib.sdk.core.web.encoding;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author andrewpettey
 * 
 */
public final class Encoder {

	private final static String UTF8_ENCODING = "UTF-8";

	private Encoder() {

		// private constructor to prevent instantiation.
	}

	public static String urlEncode(String stringToEncode) throws UnsupportedEncodingException {

		return URLEncoder.encode(stringToEncode, UTF8_ENCODING);
	}

	public static String[] urlEncode(String[] arrayToEncode) throws UnsupportedEncodingException {

		String[] encodedArray = new String[arrayToEncode.length];
		for (int i = 0; i < arrayToEncode.length; i++) {
			encodedArray[i] = urlEncode(arrayToEncode[i]);
		}
		return encodedArray;
	}
}
