package com.mlife.web.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by milagro on 10/10/2017.
 */

public class GetPostPaymentDetailsResponse {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private GetPostPaymentDetailsData data;
    @SerializedName("action")
    @Expose
    private String action;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public GetPostPaymentDetailsData getData() {
        return data;
    }

    public void setData(GetPostPaymentDetailsData data) {
        this.data = data;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

}
