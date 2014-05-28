package com.tedx.capetown.lib.sdk.dto;

/**
 * Created by andrewpettey on 2014/05/08.
 */
public class TalkCollectionDTO extends AbstractCollectionDTO<TalkDTO> {
    TalkDTO[] talks;
    @Override
    public TalkDTO[] getCollection() {
        return talks;
    }

    @Override
    public void setCollection(TalkDTO[] collection) {
        talks = collection;
    }
    public void setTalks(TalkDTO[] collection) {
        talks = collection;
    }
}
