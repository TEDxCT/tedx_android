package com.tedx.capetown.app.core.converter.impl;

import com.tedx.capetown.app.core.converter.Converter;
import com.tedx.capetown.app.core.models.SponsorModel;
import com.tedx.capetown.lib.sdk.dto.SponsorDTO;

public class SponsorConverter extends AbstractConverter<SponsorDTO, SponsorModel> implements Converter<SponsorDTO,SponsorModel>
{

    public SponsorConverter(Class<SponsorDTO> sourceClass, Class<SponsorModel> targetClass)
    {
        super(sourceClass, targetClass);
    }

    @Override
    public SponsorModel convert(SponsorDTO source) {
        SponsorModel model = new SponsorModel();
        model.DateCreated = source.dateCreated;
        model.DateModified = source.dateModified;
        model.ImageURL = source.imageURL;
        model.WebsiteURL = source.websiteURL;
        model.DescriptionHTML = source.descriptionHTML;
        model.Name = source.name;
        return model;
    }

}