package com.tedx.capetown.app.facade.impl;

import android.content.Context;

import com.tedx.capetown.app.core.service.storage.StorageKey;
import com.tedx.capetown.app.core.service.storage.impl.StorageService;
import com.tedx.capetown.app.facade.storage.StorageFacade;

import java.io.IOException;

/**
 * Created by andrewpettey on 2014/06/25.
 */

public class StorageFacadeImpl implements StorageFacade {

    private StorageService service;

    public StorageFacadeImpl() {

        service = new StorageService();
    }

    @Override
    public void save(StorageKey key, Object value) throws IOException {

        service.save(key, value);
    }

    @Override
    public Object read(StorageKey key) {
        Object data;
        try{
            data = service.read(key);
            return data;
        }
        catch(Exception e)
        {
            return null;
        }
    }

    @Override
    public boolean readAsBool(StorageKey key) throws IOException {

        Object flag = read(key);
        if (flag.getClass() == Boolean.class) {
            return (Boolean) flag;
        }

        // this caters for the case where a storageKey enum value wasn't set to Boolean type
        return Boolean.parseBoolean(flag.toString());
    }

    @Override
    public void remove(StorageKey key) {

        service.remove(key);
    }
}