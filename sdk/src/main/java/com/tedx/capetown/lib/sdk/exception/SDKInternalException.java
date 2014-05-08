/**
 * 
 */
package com.tedx.capetown.lib.sdk.exception;

/**
 * @author andrewpettey
 * 
 */
@SuppressWarnings("serial")
public class SDKInternalException extends Exception {

	public SDKInternalException(String message, int errorCode) {

		super(message);
		this.errorCode = errorCode;
	}

	public SDKInternalException(String message, int errorCode, Throwable cause) {

		super(message, cause);
		this.errorCode = errorCode;
	}

	public int errorCode;
}
