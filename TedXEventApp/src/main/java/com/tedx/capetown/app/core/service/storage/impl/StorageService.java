/**
 * 
 */
package com.tedx.capetown.app.core.service.storage.impl;

import java.io.IOException;

import android.content.Context;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tedx.capetown.app.core.service.factory.impl.StorageFactoryImpl;
import com.tedx.capetown.app.core.service.storage.Storage;
import com.tedx.capetown.app.core.service.storage.StorageKey;
import com.tedx.capetown.lib.sdk.dto.DTO;

/**
 * @author andrewpettey
 * 
 */
public class StorageService {

	private static final String TAG = StorageService.class.getName();
	private Storage defaultStorageStrategy;

	public StorageService() {

		defaultStorageStrategy = new StorageFactoryImpl().createSharedPrefStorage();
	}
	private <T extends DTO> void cacheDTO(String key, T dto) throws JsonProcessingException {

		String dtoJson = new
                ObjectMapper().writeValueAsString(dto);
		defaultStorageStrategy.save(key, dtoJson);
	}

	private <T> T retrieveDTO(String key, Class<T> dtoType)
			throws JsonParseException, JsonMappingException, IOException {

		String dtoJson = (String) defaultStorageStrategy.read(key);
		if (dtoJson != null && dtoJson.length() != 0) {
			return new ObjectMapper().readValue(dtoJson, dtoType);
		}
		return null;

	}

	public Object read(StorageKey key) throws IOException {

		String storageKey = key.getStorageKey();

		if (key.getStorageClass().getSuperclass() == DTO.class) {
			return retrieveDTO(storageKey, key.getStorageClass());

		}
		else if (key.getStorageClass() == Boolean.class) {
			return Boolean.parseBoolean(defaultStorageStrategy.read(storageKey).toString());

		} else {
			return defaultStorageStrategy.read(storageKey);
		}

	}

	public void save(StorageKey key, Object value) throws IllegalArgumentException, IOException {

		String storageKey = key.getStorageKey();

		if (key.getStorageClass().getSuperclass() == DTO.class) {
			if (value.getClass().getSuperclass() != DTO.class) {
				throw new IllegalArgumentException(String.format(
						"Can't save value to storage, the storageKey enum '%s' expects a DTO type", key.name()));
			}
			cacheDTO(storageKey, (DTO) value);

		}
		else if (key.getStorageClass() == Boolean.class) {
			if (value.getClass() != Boolean.class) {
				throw new IllegalArgumentException(String.format(
						"Can't save value to storage, the storageKey enum '%s' expects a Boolean type", key.name()));
			}
			defaultStorageStrategy.save(storageKey, value.toString());

		}
		else {
			// Default to converting value to string and saving
			defaultStorageStrategy.save(storageKey, value.toString());
		}
	}

	public void remove(StorageKey key) {

        defaultStorageStrategy.remove(key.getStorageKey());
	}
}
