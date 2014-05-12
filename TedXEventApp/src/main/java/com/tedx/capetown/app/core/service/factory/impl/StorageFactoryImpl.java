/**
 * 
 */
package com.tedx.capetown.app.core.service.factory.impl;

import android.content.Context;

import com.tedx.capetown.app.core.service.factory.StorageFactory;
import com.tedx.capetown.app.core.service.storage.impl.SharedPrefStorageImpl;

/**
 * @author AndrewPettey
 * 
 */
public class StorageFactoryImpl implements StorageFactory {

	private Context context;

	public StorageFactoryImpl(Context context) {

		this.context = context.getApplicationContext();
	}

	@Override
	public SharedPrefStorageImpl createSharedPrefStorage() {

		return new SharedPrefStorageImpl(context);
	}
}
