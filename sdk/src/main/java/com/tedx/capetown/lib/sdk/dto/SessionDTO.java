package com.tedx.capetown.lib.sdk.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by andrewpettey on 2014/05/08.
 */
//, value = {"talks","id","dateCreated","dateModified","isActive","endTime","startTime","name","eventId"}
@JsonIgnoreProperties(ignoreUnknown = true)
public class SessionDTO extends DTO {
    public int id;
    public String dateCreated;
    public String dateModified;
    public String isActive;
    public String startTime;
    public String endTime;
    public String name;
    public String eventId;
    public List<TalkDTO> talks;
}
