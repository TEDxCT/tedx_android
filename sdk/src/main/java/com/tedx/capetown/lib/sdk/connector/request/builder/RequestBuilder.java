package com.tedx.capetown.lib.sdk.connector.request.builder;

import java.io.UnsupportedEncodingException;

/**
 * Defines a base interface for any request builder.
 * 
 * @author andrewpettey
 * 
 * @param <T> the type of the request object that will be built by a builder implementing this interface
 */
public interface RequestBuilder<T> {

	/**
	 * Instantiates a new instance of type <code>T</code>, initialising it with the parameters set on the builder
	 * instance.
	 * 
	 * @return a new request object
	 * @throws java.io.UnsupportedEncodingException
	 */
	T build() throws UnsupportedEncodingException;

}
