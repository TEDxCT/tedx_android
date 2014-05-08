/**
 * 
 */
package com.tedx.capetown.lib.sdk.exception;

/**
 * @author andrewpettey
 * 
 */
@SuppressWarnings("serial")
public class SDKServerException extends SDKInternalException {

	/**
	 * @param message
	 * @param errorCode
	 */
	public SDKServerException(String message, int errorCode) {

		super(message, errorCode);
	}

}
