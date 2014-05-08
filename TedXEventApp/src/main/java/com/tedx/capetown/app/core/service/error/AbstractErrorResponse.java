/**
 * 
 */
package com.tedx.capetown.app.core.service.error;

/**
 * @author andrewpettey
 * 
 */
public abstract class AbstractErrorResponse<TRequest> {

	public AbstractErrorResponse(TRequest request, Exception e) {

		this.request = request;
		this.e = e;
	}

	private final Exception e;

	public final Exception getException() {

		return e;
	}

	private final TRequest request;

	public final TRequest getRequest() {

		return request;
	}

}
