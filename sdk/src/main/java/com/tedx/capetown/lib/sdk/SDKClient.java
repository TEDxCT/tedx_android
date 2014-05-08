package com.tedx.capetown.lib.sdk;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by andrewpettey on 2014/05/02.
 */
public class SDKClient {
    public static final URI DEFAULT_API_ROOT = URI.create("http://95.85.26.105/tedx_server/query/");
    private Map<String, String> customRequestHeaders = null;
    public URI getAPIRoot() throws MalformedURLException {
        return DEFAULT_API_ROOT;
    }

    public String getUserAgent() {

        return "TedxCPTAndroid";
    }

    public Map<String,String> copyCustomRequestHeaders() {
        return customRequestHeaders==null?null: new HashMap<String,String>(customRequestHeaders);
    }

    public void setCustomRequestHeader(String customHeaderKey, String customHeaderValue){
        if(customHeaderKey==null || customHeaderValue==null)
            throw new IllegalArgumentException("customHeaderKey and/or customHeaderValue cannot be null");

        if(!customHeaderKey.startsWith("X-"))
            throw new IllegalArgumentException("customHeaderKey must start with X-");

        if(customRequestHeaders==null)
            customRequestHeaders = new HashMap();

        //Add or Update the custom header in the map
        customRequestHeaders.put(customHeaderKey, customHeaderValue);
    }
}
