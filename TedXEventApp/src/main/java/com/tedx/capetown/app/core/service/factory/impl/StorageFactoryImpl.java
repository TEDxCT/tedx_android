/**
 * 
 */
package com.tedx.capetown.app.core.service.factory.impl;

import android.content.Context;

import com.tedx.capetown.app.DefaultApplication;
import com.tedx.capetown.app.core.service.factory.StorageFactory;
import com.tedx.capetown.app.core.service.storage.impl.SharedPrefStorageImpl;

/**
 * @author AndrewPettey
 * 
 */
public class StorageFactoryImpl implements StorageFactory {

	private Context context;

	public StorageFactoryImpl() {

		this.context = DefaultApplication.getAppContext();
	}

	@Override
	public SharedPrefStorageImpl createSharedPrefStorage() {

		return new SharedPrefStorageImpl(context);
	}
}
