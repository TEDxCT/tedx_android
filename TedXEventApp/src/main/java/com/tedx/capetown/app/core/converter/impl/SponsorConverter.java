package com.tedx.capetown.app.core.converter.impl;

import com.tedx.capetown.app.core.converter.Converter;
import com.tedx.capetown.app.core.models.SponsorModel;
import com.tedx.capetown.lib.sdk.dto.SponsorDTO;

public class SponsorConverter extends AbstractConverter<SponsorDTO, SponsorModel> implements Converter<SponsorDTO,SponsorModel> {
    public SponsorConverter(Class<SponsorDTO> sourceClass, Class<SponsorModel> targetClass) {
        super(sourceClass, targetClass);
    }

    @Override
    public SponsorModel convert(SponsorDTO source) {
        SponsorModel sponsorModel = new SponsorModel();
        sponsorModel.DateCreated = source.dateCreated;
        sponsorModel.DateModified = source.dateModified;
       // sponsorModel.IsActive = (boolean) source.isActive;
        sponsorModel.ImageURL = source.imageURL;
        sponsorModel.WebsiteURL = source.websiteURL;
        sponsorModel.DescriptionHTML = source.descriptionHTML;
        sponsorModel.Name = source.name;
        return sponsorModel;
    }

}