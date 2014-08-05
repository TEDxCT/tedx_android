package com.tedx.capetown.lib.sdk;

import com.tedx.capetown.lib.sdk.connector.EventConnector;
import com.tedx.capetown.lib.sdk.connector.SpeakerConnector;
import com.tedx.capetown.lib.sdk.connector.SponsorConnector;
import com.tedx.capetown.lib.sdk.factory.SDKFactory;
import com.tedx.capetown.lib.sdk.factory.impl.SDKFactoryImpl;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by andrewpettey on 2014/05/02.
 */
public class SDKClient {
    public static final URI DEFAULT_API_ROOT = URI.create("http://api.tedxcapetown.org/");
    private static URI API_ROOT;
    private Map<String, String> customRequestHeaders = null;
    public SDKClient()
    {
        API_ROOT = DEFAULT_API_ROOT;
    }

    public URI getAPIRoot() throws MalformedURLException {
        return DEFAULT_API_ROOT;
    }
    public void setAPIRoot(URI path) throws MalformedURLException, URISyntaxException {
        API_ROOT =  path;
    }
    private SDKFactory sdkFactory = new SDKFactoryImpl(this);

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
    public SpeakerConnector getSpeakerConnector() {
        return sdkFactory.createSpeakerConnector();
    }

    public SponsorConnector getSponsorConnector() {
        return sdkFactory.createSponsorConnector();
    }

    public EventConnector getEventConnector() {
        return sdkFactory.createEventConnector();
    }

}
