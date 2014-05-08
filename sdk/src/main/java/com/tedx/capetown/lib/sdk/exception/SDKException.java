/**
 * 
 */
package com.tedx.capetown.lib.sdk.exception;

import com.tedx.capetown.lib.sdk.dto.ErrorDTO;

import java.util.Map;


/**
 * @author andrewpettey
 * 
 */
@SuppressWarnings("serial")
public class SDKException extends Exception {

	public static final String DEFAULT_USER_MESSAGE = "An error has occurred.";
	private String _url;
	private Object _serverResponse;
	private Map<String, String> validationErrors;

	public String getUrl() {

		return _url;
	}

	public void setUrl(String value) {

		_url = value;
	}

	public Object getServerResponse() {

		return _serverResponse;
	}

	public void setServerResponse(Object serverResponse) {

		_serverResponse = serverResponse;
	}

	public Map<String, String> getValidationErrors() {

		return validationErrors;
	}

	public void setValidationErrors(Map<String, String> validationErrors) {

		this.validationErrors = validationErrors;
	}

	public SDKException(Exception cause) {

		super(DEFAULT_USER_MESSAGE, cause);
	}

	public SDKException(String message, Exception cause) {

		super(message, cause);
	}

	public SDKException(ErrorDTO errorDto) {

		super(errorDto.getUserMessage(), createInnerException(errorDto));
	}

	public SDKException(String developerMessage, int errorCode) {

		super(DEFAULT_USER_MESSAGE, createInnerException(developerMessage, errorCode, null));
	}

	public SDKException(String developerMessage, int errorCode, Exception innerException) {

		super(DEFAULT_USER_MESSAGE, createInnerException(developerMessage, errorCode, innerException));
	}

	public SDKException(String userMessage, String developerMessage, int errorCode) {

		super(userMessage, createInnerException(developerMessage, errorCode, null));
	}

	private static Exception createInnerException(ErrorDTO errorDto) {

		return createInnerException(errorDto.getDeveloperMessage(), errorDto.getErrorCode(), null);
	}

	private static Exception createInnerException(String developerMessage, int errorCode, Exception innerException) {

		if (errorCode >= 400 && errorCode < 500) {

			SDKClientException clientException = new SDKClientException(developerMessage, errorCode);

			if (innerException != null) {

				clientException.initCause(innerException);
			}

			return clientException;
		}
		else if (errorCode >= 500 && errorCode < 600) {
			SDKServerException serverException = new SDKServerException(developerMessage, errorCode);

			if (innerException != null) {

				serverException.initCause(innerException);
			}
			return serverException;

		}
		else {
			SDKInternalException internalSdkException = new SDKInternalException(developerMessage, errorCode);

			if (innerException != null) {
				internalSdkException.initCause(innerException);
			}
			return internalSdkException;
		}
	}
}
