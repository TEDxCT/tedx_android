package com.tedx.capetown.app.core.converter.impl;

import com.tedx.capetown.app.core.converter.Converter;
import com.tedx.capetown.app.core.models.EventModel;
import com.tedx.capetown.lib.sdk.dto.EventDTO;

/**
 * Created by andrewpettey on 2014/05/11.
 */
public class EventConverter extends AbstractConverter<EventDTO, EventModel> implements Converter<EventDTO,EventModel> {
    public EventConverter(Class<EventDTO> sourceClass, Class<EventModel> targetClass) {
        super(sourceClass, targetClass);
    }

    @Override
    public EventModel convert(EventDTO source) {
        return null;
    }
}
