/**
 * 
 */
package com.tedx.capetown.app.facade;

import java.io.IOException;

import com.kalahari.arilou.core.service.storage.StorageKey;

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
