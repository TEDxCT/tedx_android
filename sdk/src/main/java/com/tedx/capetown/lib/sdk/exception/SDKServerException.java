/**
 * 
 */
package com.tedx.capetown.lib.sdk.exception;

/**
 * @author richardpj
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
