package com.tedx.capetown.app.core.converter.impl;

import com.tedx.capetown.app.core.converter.Converter;
import com.tedx.capetown.app.core.models.SpeakerModel;
import com.tedx.capetown.lib.sdk.dto.SpeakerDTO;

/**
 * Created by andrewpettey on 2014/05/11.
 */
public class SpeakerConverter extends AbstractConverter<SpeakerDTO, SpeakerModel> implements Converter<SpeakerDTO,SpeakerModel> {
    public SpeakerConverter(Class<SpeakerDTO> sourceClass, Class<SpeakerModel> targetClass) {
        super(sourceClass, targetClass);
    }

    @Override
    public SpeakerModel convert(SpeakerDTO source) {
        return null;
    }
}
