package com.mlife.web.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by milagro on 10/10/2017.
 */

public class GetPostPaymentDetailsData {

    @SerializedName("details")
    @Expose
    private List<GetPostPaymentDetailsDetails> details = null;

    public List<GetPostPaymentDetailsDetails> getDetails() {
        return details;
    }

    public void setDetails(List<GetPostPaymentDetailsDetails> details) {
        this.details = details;
    }

}