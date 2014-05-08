/**
 * 
 */
package com.tedx.capetown.app.core.service.storage;

import com.tedx.capetown.lib.sdk.dto.DTO;

/**
 * @author andrewpettey
 * 
 */
/*
 * public enum StorageKey { Session, Onboarding, Connect, Cart, RootCategories; }
 */

public enum StorageKey {
	Session("DTO", DTO.class);

	private final String storageKey;
	private final Class storageClass;

	private StorageKey() {

		storageKey = name();
		storageClass = String.class;
	}

	private <T> StorageKey(String storageKey) {

		this(storageKey, String.class);
	}

	private <T> StorageKey(String storageKey, Class<T> storageClass) {

		this.storageKey = storageKey;
		this.storageClass = storageClass;
	}

	public String getStorageKey(String seedValue) {

		return String.format(storageKey, seedValue);
	}

	public String getStorageKey() {

		return storageKey;
	}

	public Class getStorageClass() {

		return storageClass;
	}

}
