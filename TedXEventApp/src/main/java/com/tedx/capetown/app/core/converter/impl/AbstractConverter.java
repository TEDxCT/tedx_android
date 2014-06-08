package com.tedx.capetown.app.core.converter.impl;

import android.util.Log;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by andrewpettey on 2014/05/11.
 */
public abstract class AbstractConverter<Tsource, Ttarget> {

    public AbstractConverter(Class<Tsource> sourceClass, Class<Ttarget> targetClass) {

        this.sourceClass = sourceClass;
        this.targetClass = targetClass;


    }

    private final Class<Tsource> sourceClass;
    private final Class<Ttarget> targetClass;

    protected String safeNullProtector(String input)
    {
        if(input == null || input.isEmpty() || input.equals("")) {
            return "<Missing Data>";
        }
        return input;
    }

}