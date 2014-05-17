package com.tedx.capetown.lib.sdk.factory;


import com.tedx.capetown.lib.sdk.connector.EventConnector;
import com.tedx.capetown.lib.sdk.connector.SpeakerConnector;
import com.tedx.capetown.lib.sdk.connector.SponsorConnector;

public interface SDKFactory {

    SpeakerConnector createSpeakerConnector();
    EventConnector createEventConnector();
    SponsorConnector createSponsorConnector();
}
