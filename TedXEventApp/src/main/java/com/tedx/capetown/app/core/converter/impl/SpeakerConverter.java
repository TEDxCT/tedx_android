package com.tedx.capetown.app.core.converter.impl;

import com.tedx.capetown.app.core.converter.Converter;
import com.tedx.capetown.app.core.models.ContactCollectionModel;
import com.tedx.capetown.app.core.models.SpeakerModel;
import com.tedx.capetown.lib.sdk.dto.SpeakerDTO;

import java.util.List;

public class SpeakerConverter extends AbstractConverter<SpeakerDTO, SpeakerModel> implements Converter<SpeakerDTO,SpeakerModel>
{
    public SpeakerConverter(Class<SpeakerDTO> sourceClass, Class<SpeakerModel> targetClass)
    {
        super(sourceClass, targetClass);
    }

    @Override
    public SpeakerModel convert(SpeakerDTO source)
    {
        SpeakerModel model = new SpeakerModel();
        model.id = source.id;
        model.fullName = safeNullProtector(source.fullName);
        model.descriptionHTML = safeNullProtector(source.descriptionHTML);
        model.imageURL = safeNullProtector(source.imageURL);
        model.dateCreated = safeNullProtector(source.dateCreated);
        model.dateModified = safeNullProtector(source.dateModified);
        model.funkyTitle = safeNullProtector(source.funkyTitle);
        model.contactDetails = new ContactCollectionConverter(List.class, ContactCollectionModel.class).convert(source.contactDetails);
        return model;
    }


}