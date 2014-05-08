/**
 * 
 */
package com.tedx.capetown.lib.sdk.connector.request.impl;

import com.tedx.capetown.lib.sdk.web.RestMethod;

import java.io.UnsupportedEncodingException;
/**
 * @author andrewpettey
 * 
 */
public abstract class AbstractCollectionRequest extends AbstractRequest {

	public AbstractCollectionRequest(Boolean metadataOnly,
			Integer offset,
			Integer limit,
			String pathComponent) throws UnsupportedEncodingException {

		super(pathComponent);
		processCollectionParameters(metadataOnly, offset, limit);
	}

	public AbstractCollectionRequest(Boolean metadataOnly,
			Integer offset,
			Integer limit,
			String pathComponentTemplate,
			String... pathParameters) throws UnsupportedEncodingException {

		super(pathComponentTemplate, pathParameters);
		processCollectionParameters(metadataOnly, offset, limit);
	}

	public AbstractCollectionRequest(Boolean metadataOnly,
			Integer offset,
			Integer limit,
			RestMethod method,
			String pathComponentTemplate,
			String... pathParameters) throws UnsupportedEncodingException {

		super(method, pathComponentTemplate, pathParameters);
		processCollectionParameters(metadataOnly, offset, limit);
	}

	public AbstractCollectionRequest(Boolean metadataOnly,
			Integer offset,
			Integer limit,
			RestMethod method,
			String path) throws UnsupportedEncodingException {

		super(method, path);
		processCollectionParameters(metadataOnly, offset, limit);
	}

	private void processCollectionParameters(Boolean metadataOnly, Integer offset, Integer limit) throws UnsupportedEncodingException {

		if (metadataOnly != null && metadataOnly) {
			addQueryParameter("metadata_only", "true");
		}
		if (offset != null) {
			addQueryParameter("offset", offset.toString());
		}
		if (limit != null) {
			addQueryParameter("limit", limit.toString());
		}
	}
}
