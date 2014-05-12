package com.tedx.capetown.app.core.converter.impl;

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

        sourceFieldMap = AbstractConverter.fieldArrayToHashMap(this.sourceClass.getFields());
        targetFieldMap = AbstractConverter.fieldArrayToHashMap(this.targetClass.getFields());

        Set<String> fieldNameIntersection = new HashSet(sourceFieldMap.keySet());
        fieldNameIntersection.retainAll(targetFieldMap.keySet());
        this.sharedFieldNames = fieldNameIntersection.toArray(new String[0]);

    }

    private final Class<Tsource> sourceClass;
    private final Class<Ttarget> targetClass;

    private final HashMap<String, Field> sourceFieldMap;
    private final HashMap<String, Field> targetFieldMap;

    private final String[] sharedFieldNames;

    private static final HashMap<String, Field> fieldArrayToHashMap(Field[] fieldArray) {

        HashMap<String, Field> returnMap = new HashMap<String, Field>(fieldArray.length);

        for (Field item : fieldArray) {
            returnMap.put(item.getName(), item);
        }
        return returnMap;
    }

    protected void convert(Tsource source, Ttarget target) throws IllegalArgumentException,
            IllegalAccessException {

        convert(source, target, sharedFieldNames);
    }

    protected void convert(Tsource source, Ttarget target, String[] fieldMap)
            throws IllegalArgumentException, IllegalAccessException {

        for (String fieldName : fieldMap) {
            copyField(source, target, fieldName);
        }
    }

    protected void convert(Tsource source, Ttarget target, HashMap<String, String> fieldMap)
            throws IllegalArgumentException, IllegalAccessException {

        for (HashMap.Entry<String, String> fieldMapEntry : fieldMap.entrySet()) {
            copyField(source, fieldMapEntry.getKey(), target, fieldMapEntry.getValue());
        }
    }

    private void copyField(Tsource source, Ttarget target, String fieldName) throws IllegalArgumentException,
            IllegalAccessException {

        copyField(source, fieldName, target, fieldName);
    }

    private void copyField(Tsource source, String sourceFieldName, Ttarget target, String targetFieldName)
            throws IllegalArgumentException, IllegalAccessException {

        if (sourceFieldMap.containsKey(sourceFieldName) && targetFieldMap.containsKey(targetFieldName)) {
            Field sourceField = sourceFieldMap.get(sourceFieldName);
            Field targetField = targetFieldMap.get(targetFieldName);
            targetField.set(target, sourceField.get(source));
        } else {
            throw new IllegalArgumentException(
                    String.format(
                            "<%s>   Either the source (dto) or the target (model) does not contain a field with the specified name. source=%s  target=%s",
                            this.getClass().getSimpleName(), sourceFieldName, targetFieldName)
            );
        }
    }
}