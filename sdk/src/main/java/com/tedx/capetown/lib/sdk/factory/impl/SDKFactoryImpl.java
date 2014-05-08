package com.tedx.capetown.lib.sdk.factory.impl;

import com.tedx.capetown.lib.sdk.SDKClient;
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

//	/**
//	 * Returns an instance of the <code>CartConnector</code> interface.
//	 *
//	 * @return a <code>CartConnectorImpl</code> instance
//	 */
//	@Override
//	public CartConnector createCartConnector() {
//
//		return new CartConnectorImpl(client);
//	}
//
//	@Override
//	public AuthorizationConnector createAuthorizationConnector() {
//
//		return new AuthorizationConnectorImpl(client);
//	}
//
//	@Override
//	public ProductConnector createProductConnector() {
//
//		return new ProductConnectorImpl(client);
//	}
//
//	@Override
//	public NavigationConnector createNavigationConnector() {
//
//		return new NavigationConnectorImpl(client);
//	}
//
//	@Override
//	public ProfileConnector createProfileConnector() {
//
//		return new ProfileConnectorImpl(client);
//	}
//
//	@Override
//	public FilterConnector createFilterConnector() {
//
//		return new FilterConnectorImpl(client);
//	}
//
//	@Override
//	public SearchSuggestionConnector createSearchSuggestionConnector() {
//
//		return new SearchSuggestionConnectorImpl(client);
//	}
}
