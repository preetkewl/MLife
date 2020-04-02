package com.mlife.adapter;

/**
 * Created by milagro on 9/5/2017.
 */

public class LocalOffersGetterSetter {

    int id;
    String Heading, Detail, Validity, PostedBy, Date;


    public LocalOffersGetterSetter(int id, String heading, String detail, String validity, String postedBy, String date) {
        this.id = id;
        Heading = heading;
        Detail = detail;
        Validity = validity;
        PostedBy = postedBy;
        Date = date;
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

    public String getValidity() {
        return Validity;
    }

    public void setValidity(String validity) {
        Validity = validity;
    }

    public String getPostedBy() {
        return PostedBy;
    }

    public void setPostedBy(String postedBy) {
        PostedBy = postedBy;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}
