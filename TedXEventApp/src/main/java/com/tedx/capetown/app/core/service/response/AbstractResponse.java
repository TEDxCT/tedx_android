/**
 * 
 */
package com.tedx.capetown.app.core.service.response;

/**
 * @author andrewpettey
 * 
 */
public abstract class AbstractResponse<TRequest, TResponse> {

	public AbstractResponse(TRequest request, TResponse response) {

		this.request = request;
		this.response = response;
	}

	private final TRequest request;

	public final TRequest getRequest() {

		return request;
	}

	private final TResponse response;

	public TResponse getResponse() {

		return response;
	}
}
