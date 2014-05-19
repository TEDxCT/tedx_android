/**
 * 
 */
package com.tedx.capetown.lib.sdk.web.impl;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tedx.capetown.lib.sdk.dto.ErrorDTO;
import com.tedx.capetown.lib.sdk.exception.SDKException;
import com.tedx.capetown.lib.sdk.web.RestConnection;

/**
 * @author andrewpettey
 * 
 */
public class RestConnectionImpl implements RestConnection {

	private int _connectionTimeout;
	private int _readTimeout;
	private String _headerAccept;
	private String _headerContentType;
	private String _headerUserAgent = null;
	private Map<String, String> additionalRequestHeaders = null;

	public RestConnectionImpl()
	{

		_headerAccept = "application/json";
		_headerContentType = "application/json;charset=UTF-8";
		_connectionTimeout = 10000;
		_readTimeout = 10000;

	}

	@Override
	public int getConnectionTimeout() {

		return _connectionTimeout;
	}

	@Override
	public void setConnectionTimeout(int value) {

		_connectionTimeout = value;
	}

	@Override
	public int getReadTimeout() {

		return _readTimeout;
	}

	@Override
	public void setReadTimeout(int value) {

		_readTimeout = value;
	}

	@Override
	public String getHeaderAccept() {

		return _headerAccept;
	}

	@Override
	public void setHeaderAccept(String value) {

		_headerAccept = value;
	}

	@Override
	public String getHeaderContentType() {

		return _headerContentType;
	}

	@Override
	public void setHeaderContentType(String value) {

		_headerContentType = value;
	}

	@Override
	public String getHeaderUserAgent() {

		return _headerUserAgent;
	}

	@Override
	public void setHeaderUserAgent(String value) {

		_headerUserAgent = value;
	}

	/**
	 * @return the additionalRequestHeaders
	 */
	@Override
	public Map<String, String> getAdditionalRequestHeaders() {

		return additionalRequestHeaders;
	}

	/**
	 * @param additionalRequestHeaders the additionalRequestHeaders to set
	 */
	@Override
	public void setAdditionalRequestHeaders(Map<String, String> additionalRequestHeaders) {

		this.additionalRequestHeaders = additionalRequestHeaders;
	}

	private URLConnection CreateConnection(URL url) throws IOException,
			NoSuchAlgorithmException,
			KeyManagementException {

		URLConnection connection = url.openConnection();
		setConnectionProperties(connection);
		if (additionalRequestHeaders != null) {
			addAdditionalRequestHeaders(connection);
		}
		return connection;
	}

	private void setConnectionProperties(URLConnection connection) {

		connection.setConnectTimeout(_connectionTimeout);
		connection.setReadTimeout(_readTimeout);
		connection.setRequestProperty("Accept", _headerAccept);
		connection.setRequestProperty("Content-Type", _headerContentType);
		String defaultUserAgent = System.getProperty("http.agent");
		connection.setRequestProperty("User-Agent", String.format("%s %s", _headerUserAgent, defaultUserAgent));
	}

	/**
	 * @param connection
	 */
	private void addAdditionalRequestHeaders(URLConnection connection) {

		for (Map.Entry<String, String> header : additionalRequestHeaders.entrySet()) {
			connection.setRequestProperty(header.getKey(), header.getValue());
		}
	}

	@Override
	public RestResponse Get(URL url) throws IOException,
			SDKException,
			KeyManagementException,
			NoSuchAlgorithmException {

		HttpURLConnection connection = (HttpURLConnection) CreateConnection(url);
		connection.setInstanceFollowRedirects(false);
		connection.setRequestMethod("GET");
		connection.connect();

		checkResponseForError(connection);

		return new RestResponse(connection.getInputStream(), connection.getHeaderFields());
	}

	@Override
	public RestResponse Post(URL url, String data) throws SDKException,
			IOException,
			KeyManagementException,
			NoSuchAlgorithmException {

		if (data == null) {
			data = "{}"; // necessary because Android does not support empty post body.
		}

		byte[] postBytes = data.getBytes();

		HttpURLConnection connection = (HttpURLConnection) CreateConnection(url);
		connection.setRequestMethod("POST");
		connection.setDoOutput(true);
		connection.setUseCaches(false);
		connection.setRequestProperty("Content-Length", String.valueOf(postBytes.length));
		connection.connect();

		DataOutputStream requestBodyStream = new DataOutputStream(connection.getOutputStream());
		requestBodyStream.write(postBytes);
		requestBodyStream.close();

		checkResponseForError(connection);

		return new RestResponse(connection.getInputStream(), connection.getHeaderFields());
	}
	
	@Override
	public RestResponse Put(URL url, String data) throws IOException,
			SDKException,
			KeyManagementException,
			NoSuchAlgorithmException {

		if (data == null) {
			data = "{}"; // necessary because Android does not support empty post body.
		}

		byte[] postBytes = data.getBytes();

		HttpURLConnection connection = (HttpURLConnection) CreateConnection(url);
		connection.setRequestMethod("PUT");
		connection.setDoOutput(true);
		connection.setUseCaches(false);
		connection.setRequestProperty("Content-Length", String.valueOf(postBytes.length));
		connection.connect();

		DataOutputStream requestBodyStream = new DataOutputStream(connection.getOutputStream());
		requestBodyStream.write(postBytes);
		requestBodyStream.close();

		checkResponseForError(connection);

		return new RestResponse(connection.getInputStream(), connection.getHeaderFields());
	}

	private void checkResponseForError(HttpURLConnection connection) throws SDKException, IOException {

		int responseCode = connection.getResponseCode();

		if (responseCode != 200) {
			try {
				InputStream errorStream = connection.getErrorStream();
				if (errorStream == null) {
					throw new SDKException("Error Stream is null", connection.getResponseCode());
				}

				String errorString = convertStreamToString(errorStream);
				ErrorDTO error = new ObjectMapper().readValue(errorString, ErrorDTO.class);

				SDKException sdkException = new SDKException(error);
				sdkException.setUrl(connection.getURL().toString());

				// String errorStreamString = new Scanner(errorStream, "UTF-8").useDelimiter("\\A").next();
				sdkException.setServerResponse(errorString);
				sdkException.setValidationErrors(error.getValidationErrors());
				throw sdkException;
			} catch (SDKException sdkException) {
				// just throw it up (otherwise the catch all exceptions below will wrap it in another sdkException)
				throw sdkException;
			} catch (Exception e) {

				SDKException sdkException = new SDKException("Thrown error when making error",
						connection.getResponseCode(),
						e);
				sdkException.setUrl(connection.getURL().toString());
				throw sdkException;
			}
		}
	}

	static String convertStreamToString(InputStream is) {

		java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
		return s.hasNext() ? s.next() : "";
	}



}
