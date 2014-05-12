package com.tedx.capetown.lib.sdk.connector.request.builder.impl;


import com.tedx.capetown.lib.sdk.connector.request.builder.CollectionBasedRequestBuilder;

public abstract class AbstractCollectionBasedRequestBuilder<T> extends AbstractRequestBuilder<T> implements
        CollectionBasedRequestBuilder<T> {

	public AbstractCollectionBasedRequestBuilder() {

		super();
	}

	private Boolean metadataOnly = null;
	private Integer offset = null;
	private Integer limit = null;

	private String[] filters = null;

	/**
	 * Returns the value of the metadata-only flag.
	 * 
	 * @return the value of the metadata-only flag
	 */
	protected Boolean getMetadataOnly() {

		return metadataOnly;
	}

	@Override
	public CollectionBasedRequestBuilder<T> setMetadataOnly() {

		metadataOnly = Boolean.TRUE;
		return this;
	}

	/**
	 * Returns the value of the offset parameter.
	 * 
	 * @return the value of the offset parameter
	 */
	protected Integer getOffset() {

		return offset;
	}

	@Override
	public CollectionBasedRequestBuilder<T> setOffset(Integer offset) {

		this.offset = offset;
		return this;
	}

	@Override
	public CollectionBasedRequestBuilder<T> setFilters(String[] filters) {

		this.filters = filters;
		return this;
	}

	protected String[] getFilters() {

		return filters;

	}

	/**
	 * Returns the value of the limit parameter.
	 * 
	 * @return the value of the limit parameter
	 */
	protected Integer getLimit() {

		return limit;
	}

	@Override
	public CollectionBasedRequestBuilder<T> setLimit(Integer limit) {

		this.limit = limit;
		return this;
	}
}
