package com.mlife.web.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by milagro on 12/26/2017.
 */

public class GetProfileDetailsDetails {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("university")
    @Expose
    private String university;
    @SerializedName("degree")
    @Expose
    private String degree;
    @SerializedName("yearFrom")
    @Expose
    private String yearFrom;
    @SerializedName("yearTo")
    @Expose
    private String yearTo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getYearFrom() {
        return yearFrom;
    }

    public void setYearFrom(String yearFrom) {
        this.yearFrom = yearFrom;
    }

    public String getYearTo() {
        return yearTo;
    }

    public void setYearTo(String yearTo) {
        this.yearTo = yearTo;
    }

}