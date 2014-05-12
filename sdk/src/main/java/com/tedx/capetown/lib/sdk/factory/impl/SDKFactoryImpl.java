package com.tedx.capetown.lib.sdk.factory.impl;

import com.tedx.capetown.lib.sdk.SDKClient;
import com.tedx.capetown.lib.sdk.connector.SpeakerConnector;
import com.tedx.capetown.lib.sdk.connector.impl.SpeakerConnectorImpl;
import com.tedx.capetown.lib.sdk.factory.SDKFactory;

public class SDKFactoryImpl implements SDKFactory {

	private final SDKClient client;

	/**
	 * Constructs a <code>SDKFactoryImpl</code> instance, initialising it with the given <code>KalShopClient</code>
	 * instance.
	 * 
	 * @param client a <code>KalShopClient</code> instance. This instance should be the same one the SDK consumer uses
	 *        to obtain instances of the SDK interfaces, as it is passed to the created objects to allow them to
	 *        retrieve configuration such as the API root.
	 */
	public SDKFactoryImpl(SDKClient client) {

		this.client = client;
	}

    @Override
    public SpeakerConnector createSpeakerConnector() {
        return new SpeakerConnectorImpl(client);
    }

}
