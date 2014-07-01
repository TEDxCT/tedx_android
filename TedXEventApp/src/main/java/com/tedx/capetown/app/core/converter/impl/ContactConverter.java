package com.tedx.capetown.app.core.converter.impl;

import com.tedx.capetown.app.core.converter.Converter;
import com.tedx.capetown.app.core.models.ContactModel;
import com.tedx.capetown.lib.sdk.dto.ContactDTO;

public class ContactConverter extends AbstractConverter<ContactDTO, ContactModel> implements Converter<ContactDTO,ContactModel>
{
    public ContactConverter(Class<ContactDTO> sourceClass, Class<ContactModel> targetClass)
    {
        super(sourceClass, targetClass);
    }

    @Override
    public ContactModel convert(ContactDTO source)
    {
        ContactModel model = new ContactModel();
        model.id = source.id;
        model.dateCreated = source.dateCreated;
        model.dateModified = source.dateModified;
        model.name = source.name;
        model.value = source.value;
        return model;
    }

}