package com.tedx.capetown.lib.sdk.dto;

import java.util.List;

/**
 * Created by andrewpettey on 2014/05/08.
 */
public class EventDTO extends DTO {
    public int id;
    public String dateCreated;
    public String dateModified;
    public String startDate;
    public String endDate;
    public String name;
    public String imageURL;
    public String websiteURL;
    public String descriptionHTML;
    public double latitude;
    public double  longitude;
    public String locationDescriptionHTML;
    public List<SessionDTO> sessions;
}
