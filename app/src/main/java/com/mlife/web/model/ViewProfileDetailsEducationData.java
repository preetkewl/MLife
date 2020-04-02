package com.mlife.web.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by milagro on 1/4/2018.
 */

public class ViewProfileDetailsEducationData {

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