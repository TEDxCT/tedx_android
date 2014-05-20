package com.tedx.capetown.app.core.converter.impl;

import android.util.Log;

import com.tedx.capetown.app.core.converter.Converter;
import com.tedx.capetown.app.core.models.SpeakerCollectionModel;
import com.tedx.capetown.app.core.models.SpeakerModel;
import com.tedx.capetown.lib.sdk.dto.SpeakerCollectionDTO;
import com.tedx.capetown.lib.sdk.dto.SpeakerDTO;


import java.util.ArrayList;
import java.util.List;

public class SpeakerCollectionConverter extends AbstractConverter<SpeakerCollectionDTO, SpeakerCollectionModel> implements Converter<SpeakerCollectionDTO,SpeakerCollectionModel> {

    public SpeakerCollectionConverter(Class<SpeakerCollectionDTO> sourceClass, Class<SpeakerCollectionModel> targetClass)
    {
        super(sourceClass, targetClass);
    }

    @Override
    public SpeakerCollectionModel convert(SpeakerCollectionDTO source) {
        Log.wtf("TEST","Source Status: ");
        Log.wtf("TEST","Source Status: "+source.getCollection()[0].fullName);
        SpeakerCollectionModel speakerCollectionModel = new SpeakerCollectionModel();
        speakerCollectionModel.speakers = new ArrayList<SpeakerModel>();

        SpeakerConverter speakerConverter = new SpeakerConverter(SpeakerDTO.class, SpeakerModel.class);
        for(SpeakerDTO speaker : source.getCollection())
        {
            SpeakerModel speakerModel = speakerConverter.convert(speaker);
            speakerCollectionModel.speakers.add(speakerModel);
        }
        return speakerCollectionModel;
    }

}
