/**
 * 
 */
package com.tedx.capetown.lib.sdk.connector.request.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tedx.capetown.lib.sdk.connector.request.Request;
import com.tedx.capetown.lib.sdk.core.web.encoding.Encoder;
import com.tedx.capetown.lib.sdk.core.web.serialization.QueryStringSerializer;
import com.tedx.capetown.lib.sdk.dto.DTO;
import com.tedx.capetown.lib.sdk.web.RestMethod;

/**
 * @author andrewpettey
 * 
 */
public abstract class AbstractRequest implements Request {

	private final RestMethod method;
	private final String pathComponent;
	private Map<String, String> queryParams = new LinkedHashMap<String, String>();
	private Map<String, String> requestCookies = new LinkedHashMap<String, String>();
	private Map<String, String> requestHeaders = new LinkedHashMap<String, String>();
	private DTO dataDTO = null;

	public AbstractRequest(String pathComponent) {

		this(RestMethod.GET, pathComponent);
	}

	public AbstractRequest(String pathComponentTemplate, String... pathParameters) throws UnsupportedEncodingException {

		this(RestMethod.GET, pathComponentTemplate, pathParameters);
	}

	public AbstractRequest(RestMethod method,
			String pathComponentTemplate,
			String... pathParameters) throws UnsupportedEncodingException {

		this(method, String.format(pathComponentTemplate, (Object[]) Encoder.urlEncode(pathParameters)));
	}

	public AbstractRequest(RestMethod method, String path) {

		this.method = method;
		pathComponent = path;
		processSession();
	}

	private void processSession() {
	}
	@Override
	public final String getPathComponent() {

		return pathComponent;
	}

	@Override
	public final String getQueryParameters() {

		if (queryParams != null && !queryParams.isEmpty()) {
			return new QueryStringSerializer().serialize(queryParams);
		}
		return "";
	}

	protected final void addQueryParameter(String name, String value) throws UnsupportedEncodingException {

		queryParams.put(name, Encoder.urlEncode(value));
	}

	@Override
	public final String getRelativePath() throws UnsupportedEncodingException {

		StringBuilder sb = new StringBuilder(getPathComponent());
		sb.append(getQueryParameters());

		return sb.toString();
	}

	@Override
	public final RestMethod getMethod() {

		return method;
	}

	@Override
	public final String getData() throws JsonGenerationException, JsonMappingException, IOException {

		if (dataDTO != null) {
			return new ObjectMapper().writeValueAsString(dataDTO);
		}
		return null;
	}

	protected final void setData(DTO dataDTO) {

		this.dataDTO = dataDTO;
	}

	protected final void addHeader(String name, String value) {

		if (name.equalsIgnoreCase("Cookie")) {
			throw new IllegalArgumentException(
					"Cookie headers disallowed.  To send cookies with the request please use the addCookie method.");
		}
		requestHeaders.put(name, value);
	}

	protected final void addCookie(String name, String value) {

		requestCookies.put(name, value);
	}

	@Override
	public void setApiKey(String apiKey) throws UnsupportedEncodingException {

		addQueryParameter("apiKey", apiKey);
	}
}
