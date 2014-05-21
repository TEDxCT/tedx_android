package com.tedx.capetown.lib.sdk.dto;

/**
 * Created by andrewpettey on 2014/05/08.
 */
public class SessionDTO extends DTO {
    public int id;
    public String dateCreated;
    public String dateModified;
    //public boolean isActive;
    public String startTime;
    public String endTime;
    public String name;
    //public int eventId; - removed as creates object hierarchy loop
    public TalkCollectionDTO talks;
}
