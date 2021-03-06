package com.tedx.capetown.lib.sdk.connector.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tedx.capetown.lib.sdk.SDKClient;
import com.tedx.capetown.lib.sdk.connector.request.Request;
import com.tedx.capetown.lib.sdk.dto.DTO;
import com.tedx.capetown.lib.sdk.dto.SDKResponse;
import com.tedx.capetown.lib.sdk.exception.SDKException;
import com.tedx.capetown.lib.sdk.web.RestConnection;
import com.tedx.capetown.lib.sdk.web.impl.RestConnectionImpl;
import com.tedx.capetown.lib.sdk.web.impl.RestResponse;

/**
 * This class provides utility methods common to all standard connector implementations.
 *
 *
 */
public abstract class AbstractConnector {

	private final SDKClient client;

	protected AbstractConnector(SDKClient client) {

		this.client = client;
	}

	private RestConnection createConnection(Request request) throws IOException {

		RestConnectionImpl connection = new RestConnectionImpl();
		setConnectionProperties(connection);
		Map<String, String> requestHeaders = request.copyHeaders();

		Map<String, String> customClientHeaders = client.copyCustomRequestHeaders();
		if(customClientHeaders != null && customClientHeaders.size() > 0) {
		//Add any custom headers set by consuming client
			if(requestHeaders==null)
				requestHeaders = new HashMap<String, String>();

			requestHeaders.putAll(customClientHeaders);
		}

		if (requestHeaders != null) {
			connection.setAdditionalRequestHeaders(requestHeaders);
		}
		return connection;
	}

	private void setConnectionProperties(RestConnection connection) {

		connection.setHeaderAccept("application/json");
		connection.setHeaderContentType("application/json;charset=UTF-8");
	}

	protected URL getTargetUrl(Request request) throws MalformedURLException, UnsupportedEncodingException {

		URL targetUrl;
        targetUrl = new URL(getAPIRootUrl().toString()+request.getPathComponent());
		return targetUrl;
	}

	protected <T extends DTO> SDKResponse<T> sdkResponseForRequest(Request request, Class<T> dtoType)
			throws IOException, SDKException, ParseException {


		RestResponse response;
		try {
			response = responseForRequest(request);
		} catch (SDKException sdkException) {
			if (sdkException.getUrl() == null) {
				sdkException.setUrl(getTargetUrl(request).toString());
			}
            sdkException.setUrl("Abstract Connector");
			throw sdkException;
		} catch (Exception e) {
			SDKException sdkException = new SDKException("Abstract Connector:"+e.getClass(), e);
			sdkException.setUrl(getTargetUrl(request).toString());
            sdkException.setUrl("Abstract Connector1");
			throw sdkException;

		}

		return convertResponseToSdkResponse(response, dtoType);
	}

	private URL getAPIRootUrl() throws MalformedURLException {

		return client.getAPIRoot().toURL();
	}

	private RestResponse responseForRequest(Request request) throws IOException,
			SDKException,
			KeyManagementException,
			NoSuchAlgorithmException {

		URL targetUrl = getTargetUrl(request);

		RestConnection connection = createConnection(request);
            switch (request.getMethod()) {
                case GET:
                    return connection.Get(targetUrl);
                case POST:
                    return connection.Post(targetUrl, request.getData());
                case PUT:
                    return connection.Put(targetUrl, request.getData());
                default:
                    throw new IllegalArgumentException(
                            "Attempt to initiate REST connection with unimplemented HTTP method.");

            }
	}

	private <T extends DTO> T convertResponseToDTO(RestResponse response, Class<T> dtoType) throws
			IOException, SDKException {

        String json="";
		try {
			if (response.stream == null) {
				json = "{}";
			} else {
				json = convertStreamToString(response.stream);
				if (json == null || json.equals("")) {
					json = "{}";
				}
			}
            
			T responseDTO = new ObjectMapper().readValue(json, dtoType);
			response.stream.close();
			return responseDTO;
		} catch (Exception e) {
			SDKException sdkException = new SDKException("Converter Error"+e.getMessage(), e);
			throw sdkException;

		}
	}

	private <T extends DTO> SDKResponse<T> convertResponseToSdkResponse(RestResponse response,
			Class<T> dtoType) throws JsonParseException,
			JsonMappingException,
			IOException,
			SDKException,
			ParseException {

		T responseDTO = convertResponseToDTO(response, dtoType);
		return new SDKResponse(responseDTO);
	}


	static String convertStreamToString(java.io.InputStream is) {

		java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
		return s.hasNext() ? s.next() : "";
	}

}
