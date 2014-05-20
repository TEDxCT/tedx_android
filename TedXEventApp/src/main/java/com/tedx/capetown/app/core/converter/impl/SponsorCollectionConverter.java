package com.tedx.capetown.app.core.converter.impl;

import com.tedx.capetown.app.core.converter.Converter;
import com.tedx.capetown.app.core.models.SponsorCollectionModel;
import com.tedx.capetown.app.core.models.SponsorModel;
import com.tedx.capetown.lib.sdk.dto.SponsorCollectionDTO;
import com.tedx.capetown.lib.sdk.dto.SponsorDTO;

import java.util.ArrayList;

public class SponsorCollectionConverter extends AbstractConverter<SponsorCollectionDTO, SponsorCollectionModel> implements Converter<SponsorCollectionDTO,SponsorCollectionModel> {

    public SponsorCollectionConverter(Class<SponsorCollectionDTO> sourceClass, Class<SponsorCollectionModel> targetClass)
    {
        super(sourceClass, targetClass);
    }
   
    @Override
    public SponsorCollectionModel convert(SponsorCollectionDTO source)
    {
        SponsorCollectionModel sponsorCollectionModel = new SponsorCollectionModel();
        sponsorCollectionModel.sponsors = new ArrayList<SponsorModel>();

        SponsorConverter sponsorConverter = new SponsorConverter(SponsorDTO.class, SponsorModel.class);
        for(SponsorDTO sponsor : source.getCollection())
        {
            SponsorModel sponsorModel = sponsorConverter.convert(sponsor);
            sponsorCollectionModel.sponsors.add(sponsorModel);
        }
        return sponsorCollectionModel;
    }

}