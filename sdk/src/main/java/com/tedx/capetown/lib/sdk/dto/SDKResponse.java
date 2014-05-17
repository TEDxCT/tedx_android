/**
 * 
 */
package com.tedx.capetown.lib.sdk.dto;

/**
 * @author AndrewPettey
 * 
 */
public class SDKResponse<T extends DTO> {

	public SDKResponse(T responseDTO) {
		this.responseDTO = responseDTO;
	}

	public T responseDTO;
}
