/**
 * 
 */
package com.tedx.capetown.app.facade;

import com.tedx.capetown.app.core.service.storage.StorageKey;

import java.io.IOException;

/**
 * @author andrewpettey
 * 
 */
public interface StorageFacade {

	public void save(StorageKey key, Object value) throws IOException;

	public Object read(StorageKey key) throws IOException;

	public boolean readAsBool(StorageKey key) throws IOException;

	public void remove(StorageKey key);
}
