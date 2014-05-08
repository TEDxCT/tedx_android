package com.tedx.capetown.lib.sdk.dto;

/**
 * Created by andrewpettey on 2014/05/08.
 */
public class SpeakerCollectionDTO extends AbstractCollectionDTO<SpeakerDTO> {
    SpeakerDTO[] speakers;
    @Override
    public SpeakerDTO[] getCollection() {
        return speakers;
    }

    @Override
    public void setCollection(SpeakerDTO[] collection) {
        speakers = collection;
    }
}
