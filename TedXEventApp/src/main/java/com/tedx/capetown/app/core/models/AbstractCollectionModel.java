package com.tedx.capetown.app.core.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrewpettey on 2014/05/11.
 */
public abstract class AbstractCollectionModel<T> {

    public AbstractCollectionModel(T[] collection) {

        this.collection = new ArrayList<T>(collection.length);
    }

    public List<T> collection;
}
