package com.tedx.capetown.app.core.models;

/**
 * Created by andrewpettey on 2014/05/02.
 */
public class TalkModel {
    public int id;
    public String dateCreated;
    public String dateModified;
    public boolean isActive;
    public String name;
    public String descriptionHTML;
    public String imageURL;
    public String videoURL;
    public int orderInSession;
    public int sessionId;
    public int speakerId;
    public SpeakerModel speaker;
}
