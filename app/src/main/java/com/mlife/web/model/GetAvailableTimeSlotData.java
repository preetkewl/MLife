package com.mlife.web.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by milagro on 2/2/2018.
 */

public class GetAvailableTimeSlotData {

    @SerializedName("amentiee_timeslot_timeslot")
    @Expose
    private String amentieeTimeslotTimeslot;

    @SerializedName("amenty_is_multiple")
    @Expose
    private String amentyIsMultiple;

    @SerializedName("amenty_seats")
    @Expose
    private String amentySeats;

    @SerializedName("bookings")
    @Expose
    private String bookings;

    public String getBookings() {
        return bookings;
    }

    public void setBookings(String bookings) {
        this.bookings = bookings;
    }

    public String getAmentieeTimeslotTimeslot() {
        return amentieeTimeslotTimeslot;
    }

    public void setAmentieeTimeslotTimeslot(String amentieeTimeslotTimeslot) {
        this.amentieeTimeslotTimeslot = amentieeTimeslotTimeslot;
    }

    public String getAmentyIsMultiple() {
        return amentyIsMultiple;
    }

    public void setAmentyIsMultiple(String amentyIsMultiple) {
        this.amentyIsMultiple = amentyIsMultiple;
    }

    public String getAmentySeats() {
        return amentySeats;
    }

    public void setAmentySeats(String amentySeats) {
        this.amentySeats = amentySeats;
    }

}