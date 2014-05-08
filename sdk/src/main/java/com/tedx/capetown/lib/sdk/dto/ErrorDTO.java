/**
 * 
 */
package com.tedx.capetown.lib.sdk.dto;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = Id.NAME, include = As.WRAPPER_OBJECT)
@JsonTypeName("errorResponse")
public class ErrorDTO  extends DTO{

	private String developerMessage;
	private String userMessage;
	private int errorCode;
	private Map<String, String> validationErrors;

	public ErrorDTO() {

		// Default constructor
	}

	public ErrorDTO(String developerMessage, String userMessage, int errorCode) {

		this.developerMessage = developerMessage;
		this.userMessage = userMessage;
		this.errorCode = errorCode;
	}

	public String getDeveloperMessage() {

		return developerMessage;
	}

	public void setDeveloperMessage(String developerMessage) {

		this.developerMessage = developerMessage;
	}

	public String getUserMessage() {

		return userMessage;
	}

	public void setUserMessage(String userMessage) {

		this.userMessage = userMessage;
	}

	public int getErrorCode() {

		return errorCode;
	}

	public void setErrorCode(int errorCode) {

		this.errorCode = errorCode;
	}

	public Map<String, String> getValidationErrors() {

		return validationErrors;
	}

	public void setValidationErrors(Map<String, String> validationErrors) {

		this.validationErrors = validationErrors;
	}
}
