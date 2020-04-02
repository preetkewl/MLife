package com.mlife.web.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by milagro on 10/5/2017.
 */

public class EventListData {

    @SerializedName("event_id")
    @Expose
    private String eventId;

    @SerializedName("event_title")
    @Expose
    private String eventTitle;

    @SerializedName("event_desc")
    @Expose
    private String eventDesc;

    @SerializedName("event_loc")
    @Expose
    private String eventLoc;

    @SerializedName("event_date")
    @Expose
    private String eventDate;

    @SerializedName("user_event_interest")
    @Expose
    private String userEventInterest;

    @SerializedName("user_event_id")
    @Expose
    private String userEventId;

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getEventDesc() {
        return eventDesc;
    }

    public void setEventDesc(String eventDesc) {
        this.eventDesc = eventDesc;
    }

    public String getEventLoc() {
        return eventLoc;
    }

    public void setEventLoc(String eventLoc) {
        this.eventLoc = eventLoc;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getUserEventInterest() {
        return userEventInterest;
    }

    public void setUserEventInterest(String userEventInterest) {
        this.userEventInterest = userEventInterest;
    }

    public String getUserEventId() {
        return userEventId;
    }

    public void setUserEventId(String userEventId) {
        this.userEventId = userEventId;
    }

}