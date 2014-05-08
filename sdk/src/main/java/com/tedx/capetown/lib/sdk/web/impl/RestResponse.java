/**
 * 
 */
package com.tedx.capetown.lib.sdk.web.impl;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * @author richardpj
 * 
 */
public class RestResponse {

	public RestResponse(InputStream responseStream, Map<String, List<String>> responseHeaders) {

		this.stream = responseStream;
		this.headers = responseHeaders;
	}

	public InputStream stream;
	public Map<String, List<String>> headers;
}
