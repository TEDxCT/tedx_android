package com.tedx.capetown.lib.sdk.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by andrewpettey on 2014/05/08.
 */
//@JsonIgnoreProperties(ignoreUnknown = true)
public class SpeakerDTO extends DTO {
    public int id;
    public String dateCreated;
    public String dateModified;

    @JsonIgnoreProperties
    public String isActive;
    public String fullName;
    public String imageURL;
    public String descriptionHTML;
    public String funkyTitle;

    public List<ContactDTO> contactDetails;
}
