package com.mlife.adapter;

/**
 * Created by milagro on 8/30/2017.
 */

public class MyFeedbackGetterSetter {

    String Heading, Date;
    Boolean Status;

    public MyFeedbackGetterSetter(String heading, String date, Boolean status) {
        Heading = heading;
        Date = date;
        Status = status;
    }

    public String getHeading() {
        return Heading;
    }

    public void setHeading(String heading) {
        Heading = heading;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public Boolean getStatus() {
        return Status;
    }

    public void setStatus(Boolean status) {
        Status = status;
    }
}
