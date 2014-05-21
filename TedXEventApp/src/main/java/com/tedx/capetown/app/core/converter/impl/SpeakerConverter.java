package com.tedx.capetown.app.core.converter.impl;

import com.tedx.capetown.app.core.converter.Converter;
import com.tedx.capetown.app.core.models.SpeakerModel;
import com.tedx.capetown.lib.sdk.dto.SpeakerDTO;

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
        model.fullName = source.fullName;
        model.descriptionHTML = source.descriptionHTML;
        model.imageURL = source.imageURL;
        model.dateCreated = source.dateCreated;
        model.dateModified = source.dateModified;
        return model;
    }

}