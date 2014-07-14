package com.tedx.capetown.app.core.converter.impl;

import com.tedx.capetown.app.core.converter.Converter;
import com.tedx.capetown.app.core.models.TalkCollectionModel;
import com.tedx.capetown.app.core.models.TalkModel;
import com.tedx.capetown.lib.sdk.dto.TalkCollectionDTO;
import com.tedx.capetown.lib.sdk.dto.TalkDTO;

import java.util.ArrayList;
import java.util.List;

public class TalkCollectionConverter extends AbstractConverter<List, TalkCollectionModel> implements Converter<List,TalkCollectionModel> {

    public TalkCollectionConverter(Class<List> sourceClass, Class<TalkCollectionModel> targetClass) {
        super(sourceClass, targetClass);
    }

    @Override
    public TalkCollectionModel convert(List source) {
        TalkCollectionModel talkCollectionModel = new TalkCollectionModel();
        talkCollectionModel.talks = new ArrayList<TalkModel>();

        TalkConverter talkConverter = new TalkConverter(TalkDTO.class, TalkModel.class);
        List<TalkDTO> newSource = (List<TalkDTO>)source;
        if(newSource!=null && newSource.size()>0) {
            for (TalkDTO talk : newSource)
                talkCollectionModel.talks.add(talkConverter.convert(talk));
        }
        return talkCollectionModel;
    }
}