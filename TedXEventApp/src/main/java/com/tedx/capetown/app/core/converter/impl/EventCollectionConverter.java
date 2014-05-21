package com.tedx.capetown.app.core.converter.impl;

import com.tedx.capetown.app.core.converter.Converter;
import com.tedx.capetown.app.core.models.EventCollectionModel;
import com.tedx.capetown.app.core.models.EventModel;
import com.tedx.capetown.lib.sdk.dto.EventCollectionDTO;
import com.tedx.capetown.lib.sdk.dto.EventDTO;

import java.util.ArrayList;

public class EventCollectionConverter extends AbstractConverter<EventCollectionDTO, EventCollectionModel> implements Converter<EventCollectionDTO,EventCollectionModel> 
{

    public EventCollectionConverter(Class<EventCollectionDTO> sourceClass, Class<EventCollectionModel> targetClass) 
    {
        super(sourceClass, targetClass);
    }

    @Override
    public EventCollectionModel convert(EventCollectionDTO source)
    {
        EventCollectionModel eventCollectionModel = new EventCollectionModel();
        eventCollectionModel.events = new ArrayList<EventModel>();

        EventConverter eventConverter = new EventConverter(EventDTO.class, EventModel.class);
        for(EventDTO event : source.getCollection())
        {
            EventModel eventModel = eventConverter.convert(event);
            eventCollectionModel.events.add(eventModel);
        }
        return eventCollectionModel;
    }
    
}