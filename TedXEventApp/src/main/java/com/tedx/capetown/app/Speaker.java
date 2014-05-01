package com.tedx.capetown.app;

public class Speaker {

    private String speakerName;
    private String genre;
    private String talkName;
    private String description;
    private String twitterHandle;
    private String emailAddress;
    private String imageUrl;

    public Speaker(String speakerName, String genre, String talkName, String description, String twitterHandle, String emailAddress, String imageUrl) {
        this.speakerName = speakerName;
        this.genre = genre;
        this.talkName = talkName;
        this.description = description;
        this.twitterHandle = twitterHandle;
        this.emailAddress = emailAddress;
        this.imageUrl = imageUrl;
    }

    public String getSpeakerName() {
        return speakerName;
    }
    public String getGenre() {
        return genre;
    }
    public String getTalkName() {
        return talkName;
    }
    public String getDescription() {
        return description;
    }
    public String getTwitterHandle() {
        return twitterHandle;
    }
    public String getEmailAddress() {
        return emailAddress;
    }
    public String getImageURL() {
        return imageUrl;
    }
}
