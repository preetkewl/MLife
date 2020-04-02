package com.mlife.web.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by milagro on 10/7/2017.
 */

public class GetPaymentDetailsData {

    @SerializedName("details")
    @Expose
    private List<GetPaymentDetailsDetails> details = null;

    public List<GetPaymentDetailsDetails> getDetails() {
        return details;
    }

    public void setDetails(List<GetPaymentDetailsDetails> details) {
        this.details = details;
    }

}