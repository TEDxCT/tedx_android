package com.tedx.capetown.app.core.converter.impl;

import com.tedx.capetown.app.core.converter.Converter;
import com.tedx.capetown.app.core.models.SponsorCollectionModel;
import com.tedx.capetown.lib.sdk.dto.SponsorCollectionDTO;

public class SponsorCollectionConverter extends AbstractConverter<SponsorCollectionDTO, SponsorCollectionModel> implements Converter<SponsorCollectionDTO,SponsorCollectionModel> {

    public SponsorCollectionConverter(Class<SponsorCollectionDTO> sourceClass, Class<SponsorCollectionModel> targetClass) {
        super(sourceClass, targetClass);
    }

    @Override
    public SponsorCollectionModel convert(SponsorCollectionDTO source) {
        SponsorCollectionModel SponsorCollectionModel = new SponsorCollectionModel();
        return null;
    }

}