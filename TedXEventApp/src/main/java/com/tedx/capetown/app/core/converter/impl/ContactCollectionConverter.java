package com.tedx.capetown.app.core.converter.impl;

import com.tedx.capetown.app.core.converter.Converter;
import com.tedx.capetown.app.core.models.ContactCollectionModel;
import com.tedx.capetown.app.core.models.ContactModel;
import com.tedx.capetown.app.core.models.ContactCollectionModel;
import com.tedx.capetown.app.core.models.TalkModel;
import com.tedx.capetown.lib.sdk.dto.ContactDTO;
import com.tedx.capetown.lib.sdk.dto.TalkDTO;

import java.util.ArrayList;
import java.util.List;

public class ContactCollectionConverter extends AbstractConverter<List, ContactCollectionModel> implements Converter<List,ContactCollectionModel> {

    public ContactCollectionConverter(Class<List> sourceClass, Class<ContactCollectionModel> targetClass) {
        super(sourceClass, targetClass);
    }

    @Override
    public ContactCollectionModel convert(List source) {
        if (source == null)
            return null;

        ContactCollectionModel contactCollectionModel = new ContactCollectionModel();
        contactCollectionModel.contactDetails = new ArrayList<ContactModel>();

        ContactConverter contactConverter = new ContactConverter(ContactDTO.class, ContactModel.class);
        List<ContactDTO> newSource = (List<ContactDTO>)source;
        for (ContactDTO contact : newSource)
            contactCollectionModel.contactDetails.add(contactConverter.convert(contact));

        return contactCollectionModel;
    }
}