package com.tedx.capetown.app.core.converter.impl;

import com.tedx.capetown.app.core.converter.Converter;
import com.tedx.capetown.app.core.models.EventCollectionModel;
import com.tedx.capetown.lib.sdk.dto.EventCollectionDTO;

/**
 * Created by andrewpettey on 2014/05/11.
 */
public class EventCollectionConverter extends AbstractConverter<EventCollectionDTO, EventCollectionModel> implements Converter<EventCollectionDTO,EventCollectionModel> {

    public EventCollectionConverter(Class<EventCollectionDTO> sourceClass, Class<EventCollectionModel> targetClass) {
        super(sourceClass, targetClass);
    }

    @Override
    public EventCollectionModel convert(EventCollectionDTO source) {
        EventCollectionModel speakerCollectionModel = new EventCollectionModel();
        return null;
    }
}
