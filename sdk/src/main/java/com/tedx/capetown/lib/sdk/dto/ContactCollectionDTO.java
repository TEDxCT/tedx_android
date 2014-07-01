package com.tedx.capetown.lib.sdk.dto;

/**
 * Created by alexl on 2014/05/08.
 */
public class ContactCollectionDTO extends AbstractCollectionDTO<ContactDTO> {
    ContactDTO[] contactDetails;
    @Override
    public ContactDTO[] getCollection() {
        return contactDetails;
    }

    @Override
    public void setCollection(ContactDTO[] collection) {
        contactDetails = collection;
    }
    public void setTalks(ContactDTO[] collection) {
        contactDetails = collection;
    }
}
