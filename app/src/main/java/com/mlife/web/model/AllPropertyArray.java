package com.mlife.web.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by milagro on 9/29/2017.
 */

public class AllPropertyArray {

    @SerializedName("details")
    @Expose
    private List<AllPropertyData> details = null;
    @SerializedName("times")
    @Expose
    private List<AllPropertyTime> times = null;

    public List<AllPropertyData> getDetails() {
        return details;
    }

    public void setDetails(List<AllPropertyData> details) {
        this.details = details;
    }

    public List<AllPropertyTime> getTimes() {
        return times;
    }

    public void setTimes(List<AllPropertyTime> times) {
        this.times = times;
    }

}