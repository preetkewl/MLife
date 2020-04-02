package com.mlife.web.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by milagro on 10/7/2017.
 */

public class ClubHouseListData {

    @SerializedName("clubhouse_booking_id")
    @Expose
    private String clubhouseBookingId;

    @SerializedName("clubhouse_booking_title")
    @Expose
    private String clubhouseBookingTitle;

    @SerializedName("clubhouse_booking_date")
    @Expose
    private String clubhouseBookingDate;

    @SerializedName("clubhouse_booking_timeslot")
    @Expose
    private String clubhouseBookingTimeslot;

    @SerializedName("clubhouse_booking_noofpeople")
    @Expose
    private String clubhouseBookingNoofpeople;

    @SerializedName("clubhouse_booked")
    @Expose
    private String clubhouseBooked;

    @SerializedName("amenty_name")
    @Expose
    private String amentyName;

    @SerializedName("timeslot")
    @Expose
    private String timeslot;

    public String getClubhouseBookingId() {
        return clubhouseBookingId;
    }

    public void setClubhouseBookingId(String clubhouseBookingId) {
        this.clubhouseBookingId = clubhouseBookingId;
    }

    public String getClubhouseBookingTitle() {
        return clubhouseBookingTitle;
    }

    public void setClubhouseBookingTitle(String clubhouseBookingTitle) {
        this.clubhouseBookingTitle = clubhouseBookingTitle;
    }

    public String getClubhouseBookingDate() {
        return clubhouseBookingDate;
    }

    public void setClubhouseBookingDate(String clubhouseBookingDate) {
        this.clubhouseBookingDate = clubhouseBookingDate;
    }

    public String getClubhouseBookingTimeslot() {
        return clubhouseBookingTimeslot;
    }

    public void setClubhouseBookingTimeslot(String clubhouseBookingTimeslot) {
        this.clubhouseBookingTimeslot = clubhouseBookingTimeslot;
    }

    public String getClubhouseBookingNoofpeople() {
        return clubhouseBookingNoofpeople;
    }

    public void setClubhouseBookingNoofpeople(String clubhouseBookingNoofpeople) {
        this.clubhouseBookingNoofpeople = clubhouseBookingNoofpeople;
    }

    public String getClubhouseBooked() {
        return clubhouseBooked;
    }

    public void setClubhouseBooked(String clubhouseBooked) {
        this.clubhouseBooked = clubhouseBooked;
    }

    public String getAmentyName() {
        return amentyName;
    }

    public void setAmentyName(String amentyName) {
        this.amentyName = amentyName;
    }

    public String getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(String timeslot) {
        this.timeslot = timeslot;
    }
}