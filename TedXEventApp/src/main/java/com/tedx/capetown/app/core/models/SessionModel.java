package com.tedx.capetown.app.core.models;

import java.util.List;

/**
 * Created by andrewpettey on 2014/05/02.
 */
public class SessionModel {
    public int id;
    public int position;
    public boolean isActive;
    public String sessionTime;
    public String name;
    public int eventId;
    public TalkCollectionModel talks;
}
