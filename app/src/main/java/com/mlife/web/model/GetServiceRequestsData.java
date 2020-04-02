package com.mlife.web.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by milagro on 10/14/2017.
 */

public class GetServiceRequestsData {

    @SerializedName("details")
    @Expose
    private List<GetServiceRequestsDetails> details = null;

    public List<GetServiceRequestsDetails> getDetails() {
        return details;
    }

    public void setDetails(List<GetServiceRequestsDetails> details) {
        this.details = details;
    }

}