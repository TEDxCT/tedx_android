package com.tedx.capetown.lib.sdk.dto;

public class TalkDTO extends DTO {
    public String id;
    public String dateCreated;
    public String dateModified;
    public String isActive;
    public String name;
    public String imageURL;
    public String videoURL;
    public String orderInSession;
    public String descriptionHTML;
    public String websiteURL;

    public SessionDTO session;
    public SpeakerDTO speaker;
}