package com.tedx.capetown.app.core.converter.impl;

import com.tedx.capetown.app.core.converter.Converter;
import com.tedx.capetown.app.core.models.SpeakerCollectionModel;
import com.tedx.capetown.lib.sdk.dto.SpeakerCollectionDTO;

/**
 * Created by andrewpettey on 2014/05/11.
 */
public class SpeakerCollectionConverter extends AbstractConverter<SpeakerCollectionDTO, SpeakerCollectionModel> implements Converter<SpeakerCollectionDTO,SpeakerCollectionModel> {

    public SpeakerCollectionConverter(Class<SpeakerCollectionDTO> sourceClass, Class<SpeakerCollectionModel> targetClass) {
        super(sourceClass, targetClass);
    }

    @Override
    public SpeakerCollectionModel convert(SpeakerCollectionDTO source) {
        return null;
    }
}
