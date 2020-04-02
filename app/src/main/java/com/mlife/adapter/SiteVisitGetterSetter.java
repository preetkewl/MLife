package com.mlife.adapter;

/**
 * Created by milagro on 8/16/2017.
 */

public class SiteVisitGetterSetter {

    String Id, Property, Date, Time, Lat, Long, Status;

    public SiteVisitGetterSetter(String id, String property, String date, String time, String lat, String aLong, String status) {
        Id = id;
        Property = property;
        Date = date;
        Time = time;
        Lat = lat;
        Long = aLong;
        Status = status;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getProperty() {
        return Property;
    }

    public void setProperty(String property) {
        Property = property;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getLat() {
        return Lat;
    }

    public void setLat(String lat) {
        Lat = lat;
    }

    public String getLong() {
        return Long;
    }

    public void setLong(String aLong) {
        Long = aLong;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}