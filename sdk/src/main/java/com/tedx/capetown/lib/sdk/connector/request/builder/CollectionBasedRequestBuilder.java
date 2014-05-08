package com.tedx.capetown.lib.sdk.connector.request.builder;

/**
 * Defines a base interface for builders creating requests returning collections of records.
 * 
 * @author andrew pettey
 */
public interface CollectionBasedRequestBuilder<T> extends RequestBuilder<T> {

	/**
	 * Sets the flag indicating that only the collection metadata structure should be returned by the API.
	 * 
	 * @return this builder
	 */
	CollectionBasedRequestBuilder<T> setMetadataOnly();

	/**
	 * Sets the offset into the full data set to begin returning data from.
	 * 
	 * @param offset the offset value
	 * @return this builder
	 */
	CollectionBasedRequestBuilder<T> setOffset(Integer offset);

	/**
	 * Sets the upper limit on the number of records to return.
	 * 
	 * @param limit the limit value
	 * @return this builder
	 */
	CollectionBasedRequestBuilder<T> setLimit(Integer limit);

	CollectionBasedRequestBuilder<T> setFilters(String[] filters);
}
