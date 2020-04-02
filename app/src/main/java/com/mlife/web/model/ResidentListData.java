package com.mlife.web.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by milagro on 10/14/2017.
 */

public class ResidentListData {

    @SerializedName("details")
    @Expose
    private List<ResidentListDetails> details = null;

    public List<ResidentListDetails> getDetails() {
        return details;
    }

    public void setDetails(List<ResidentListDetails> details) {
        this.details = details;
    }

}