package com.tedx.capetown.app.core.converter.impl;

import android.util.Log;

import com.tedx.capetown.app.core.converter.Converter;
import com.tedx.capetown.app.core.models.SpeakerModel;
import com.tedx.capetown.app.core.models.TalkModel;
import com.tedx.capetown.lib.sdk.dto.SpeakerDTO;
import com.tedx.capetown.lib.sdk.dto.TalkDTO;

public class TalkConverter extends AbstractConverter<TalkDTO, TalkModel> implements Converter<TalkDTO,TalkModel>
{
    public TalkConverter(Class<TalkDTO> sourceClass, Class<TalkModel> targetClass)
    {
        super(sourceClass, targetClass);
    }

    @Override
    public TalkModel convert(TalkDTO source)
    {
        TalkModel model = new TalkModel();
        model.id = source.id;
        model.dateCreated = source.dateCreated;
        model.dateModified = source.dateModified;
        model.name = source.name;
        model.descriptionHTML = source.descriptionHTML;
        model.imageURL = source.imageURL;
        model.videoURL = source.videoURL;
        model.orderInSession = (source.orderInSession);
        model.speaker = new SpeakerConverter(SpeakerDTO.class, SpeakerModel.class).convert(source.speaker);
        return model;
    }

}