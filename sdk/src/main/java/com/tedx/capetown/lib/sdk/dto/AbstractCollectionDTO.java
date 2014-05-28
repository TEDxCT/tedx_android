package com.tedx.capetown.lib.sdk.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class AbstractCollectionDTO<T> extends DTO {

	public abstract T[] getCollection();

	public abstract void setCollection(T[] collection);
}
