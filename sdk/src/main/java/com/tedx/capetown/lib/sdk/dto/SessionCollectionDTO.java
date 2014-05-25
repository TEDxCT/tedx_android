package com.tedx.capetown.lib.sdk.dto;

/**
 * Created by andrewpettey on 2014/05/08.
 */
public class SessionCollectionDTO extends AbstractCollectionDTO<SessionDTO> {
    SessionDTO[] sessions;
    @Override
    public SessionDTO[] getCollection() {
        return sessions;
    }

    @Override
    public void setCollection(SessionDTO[] collection) {
sessions = collection;
    }

    public void setSessions(SessionDTO[] sessions) {
        this.sessions = sessions;
    }
}
