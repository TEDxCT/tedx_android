package com.tedx.capetown.lib.sdk.connector.request.impl;

import java.util.Map;

public class SponsorRequest extends AbstractRequest{
    public SponsorRequest(String pathComponent) {
        super(pathComponent);
    }

    @Override
    public Map<String, String> copyHeaders() {
        return null;
    }
}