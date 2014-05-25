package com.tedx.capetown.lib.sdk.dto;

/**
 * Created by andrewpettey on 2014/05/08.
 */
public class EventCollectionDTO extends AbstractCollectionDTO<EventDTO> {

    EventDTO[] events;

    @Override
    public EventDTO[] getCollection() {
        return events;
    }

    @Override
    public void setCollection(EventDTO[] collection) {
        events = collection;
    }

    public void setEvents(EventDTO[] events) {
        this.events = events;
    }
}
