package com.tedx.capetown.lib.sdk.connector.request.impl;

import java.util.Map;

/**
 * Created by andrewpettey on 2014/05/09.
 */
public class EventRequest extends AbstractRequest{
    public EventRequest(String pathComponent) {
        super(pathComponent);
    }

    @Override
    public Map<String, String> copyHeaders() {
        return null;
    }
}
