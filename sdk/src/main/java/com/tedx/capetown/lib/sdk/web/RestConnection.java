/**
 * 
 */
package com.tedx.capetown.lib.sdk.web;

import com.tedx.capetown.lib.sdk.exception.SDKException;
import com.tedx.capetown.lib.sdk.web.impl.RestResponse;

import java.io.IOException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;


/**
 * @author andrewpettey
 * 
 */
public interface RestConnection {

	public int getConnectionTimeout();

	public void setConnectionTimeout(int value);

	public int getReadTimeout();

	public void setReadTimeout(int value);

	public String getHeaderAccept();

	public void setHeaderAccept(String value);

	public String getHeaderContentType();

	public void setHeaderContentType(String value);

	public String getHeaderUserAgent();

	public void setHeaderUserAgent(String value);

	public Map<String, String> getAdditionalRequestHeaders();

	public void setAdditionalRequestHeaders(Map<String, String> additionalRequestHeaders);

	RestResponse Get(URL url) throws IOException, SDKException, KeyManagementException, NoSuchAlgorithmException;

	RestResponse Post(URL url, String data) throws IOException,
			SDKException,
			KeyManagementException,
			NoSuchAlgorithmException;

	RestResponse Put(URL url, String data) throws IOException,
			SDKException,
			KeyManagementException,
			NoSuchAlgorithmException;

}
