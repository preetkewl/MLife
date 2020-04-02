package com.mlife.adapter;

/**
 * Created by milagro on 8/22/2017.
 */

public class NotificationGetterSetter {

    int id;
    String Heading, Detail;

    public NotificationGetterSetter(int id, String heading, String detail) {
        this.id = id;
        Heading = heading;
        Detail = detail;
    }

    public NotificationGetterSetter(String heading, String detail) {
        Heading = heading;
        Detail = detail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHeading() {
        return Heading;
    }

    public void setHeading(String heading) {
        Heading = heading;
    }

    public String getDetail() {
        return Detail;
    }

    public void setDetail(String detail) {
        Detail = detail;
    }

}