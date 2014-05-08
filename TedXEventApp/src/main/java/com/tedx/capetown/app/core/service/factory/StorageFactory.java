/**
 * 
 */
package com.tedx.capetown.app.core.service.factory;


import com.tedx.capetown.app.core.service.storage.impl.SharedPrefStorageImpl;

/**
 * @author AndrewPettey
 * 
 */
public interface StorageFactory {

	public SharedPrefStorageImpl createSharedPrefStorage();

}
