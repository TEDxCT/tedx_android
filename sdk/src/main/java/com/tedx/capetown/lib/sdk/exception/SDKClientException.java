/**
 * 
 */
package com.tedx.capetown.lib.sdk.exception;

/**
 * @author richardpj
 * 
 */
@SuppressWarnings("serial")
public class SDKClientException extends SDKInternalException {

	/**
	 * @param message
	 * @param errorCode
	 */
	public SDKClientException(String message, int errorCode) {

		super(message, errorCode);
	}

}
