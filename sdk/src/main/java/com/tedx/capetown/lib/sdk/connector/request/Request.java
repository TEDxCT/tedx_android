/**
 * 
 */
package com.tedx.capetown.lib.sdk.connector.request;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.tedx.capetown.lib.sdk.web.RestMethod;

/**
 * @author andrewpettey
 * 
 */

public interface Request {

	public String getPathComponent();

	public String getQueryParameters();

	public String getRelativePath() throws UnsupportedEncodingException;

	public RestMethod getMethod();

	public String getData() throws JsonGenerationException, JsonMappingException, IOException;

	public Map<String, String> copyHeaders();

	public void setApiKey(String apiKey) throws UnsupportedEncodingException;
}
