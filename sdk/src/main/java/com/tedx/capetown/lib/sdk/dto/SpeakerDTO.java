package com.tedx.capetown.lib.sdk.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by andrewpettey on 2014/05/08.
 */
//@JsonIgnoreProperties(ignoreUnknown = true)
public class SpeakerDTO extends DTO {
    public String id;
    public String dateCreated;
    public String dateModified;
    public String isActive;
    public String fullName;
    public String imageURL;
    public String descriptionHTML;
    public String funkyTitle;
}
