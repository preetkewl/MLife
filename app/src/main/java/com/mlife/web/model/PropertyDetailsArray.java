package com.mlife.web.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by milagro on 9/22/2017.
 */

public class PropertyDetailsArray {

    @SerializedName("details")
    @Expose
    private List<PropertyDetailsData> details = null;

    public List<PropertyDetailsData> getDetails() {
        return details;
    }

    public void setDetails(List<PropertyDetailsData> details) {
        this.details = details;
    }

}