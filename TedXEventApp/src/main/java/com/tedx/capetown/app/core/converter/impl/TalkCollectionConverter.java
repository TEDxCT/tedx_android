package com.tedx.capetown.app.core.converter.impl;

import com.tedx.capetown.app.core.converter.Converter;
import com.tedx.capetown.app.core.models.TalkCollectionModel;
import com.tedx.capetown.app.core.models.TalkModel;
import com.tedx.capetown.lib.sdk.dto.TalkCollectionDTO;
import com.tedx.capetown.lib.sdk.dto.TalkDTO;

import java.util.ArrayList;

public class TalkCollectionConverter extends AbstractConverter<TalkCollectionDTO, TalkCollectionModel> implements Converter<TalkCollectionDTO,TalkCollectionModel> 
{

    public TalkCollectionConverter(Class<TalkCollectionDTO> sourceClass, Class<TalkCollectionModel> targetClass)
    {
        super(sourceClass, targetClass);
    }

    @Override
    public TalkCollectionModel convert(TalkCollectionDTO source)
    {
        TalkCollectionModel talkCollectionModel = new TalkCollectionModel();
        talkCollectionModel.talks = new ArrayList<TalkModel>();

        TalkConverter talkConverter = new TalkConverter(TalkDTO.class, TalkModel.class);
        for(TalkDTO talk : source.getCollection())
            talkCollectionModel.talks.add(talkConverter.convert(talk));

        return talkCollectionModel;
    }

}