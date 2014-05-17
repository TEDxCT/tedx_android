package com.tedx.capetown.lib.sdk.dto;

public class SponsorCollectionDTO extends AbstractCollectionDTO<SponsorDTO> {
    SponsorDTO[] sponsors;
    @Override
    public SponsorDTO[] getCollection() {
        return sponsors;
    }

    @Override
    public void setCollection(SponsorDTO[] collection) {
        sponsors = collection;
    }
}
