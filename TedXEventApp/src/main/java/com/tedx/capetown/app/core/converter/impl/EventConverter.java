package com.tedx.capetown.app.core.converter.impl;

import android.util.Log;

import com.tedx.capetown.app.core.converter.Converter;
import com.tedx.capetown.app.core.models.EventModel;
import com.tedx.capetown.app.core.models.SessionCollectionModel;
import com.tedx.capetown.app.core.models.SpeakerModel;
import com.tedx.capetown.lib.sdk.dto.EventDTO;
import com.tedx.capetown.lib.sdk.dto.SessionCollectionDTO;
import com.tedx.capetown.lib.sdk.dto.SessionDTO;
import com.tedx.capetown.lib.sdk.dto.SpeakerDTO;

import java.util.List;

public class EventConverter extends AbstractConverter<EventDTO, EventModel> implements Converter<EventDTO,EventModel>
{

   public EventConverter(Class<EventDTO> sourceClass, Class<EventModel> targetClass)
    {
        super(sourceClass, targetClass);
    }

    @Override
    public EventModel convert(EventDTO source)
    {
        EventModel model = new EventModel();
        model.descriptionHTML = source.descriptionHTML;
        model.startDate = source.startDate;
        model.endDate = source.endDate;
        model.id = source.id;
        model.imageURL = source.imageURL;
        model.latitude = source.latitude;
        model.locationDescriptionHTML = source.locationDescriptionHTML;
        model.longitude = source.longitude;
        model.hostName = source.hostName;
        model.name = source.name;
        model.websiteURL = source.websiteURL;
        model.sessions = new SessionCollectionConverter(List.class, SessionCollectionModel.class).convert(source.sessions);
        return model;
    }

}