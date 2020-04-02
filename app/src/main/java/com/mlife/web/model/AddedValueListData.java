package com.mlife.web.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by milagro on 10/7/2017.
 */

public class AddedValueListData {

    @SerializedName("value_addedservice_id")
    @Expose
    private String valueAddedserviceId;
    @SerializedName("value_addedservice_title")
    @Expose
    private String valueAddedserviceTitle;
    @SerializedName("value_addedservice_vender")
    @Expose
    private String valueAddedserviceVender;
    @SerializedName("value_addedservice_attachment")
    @Expose
    private String valueAddedserviceAttachment;

    public String getValueAddedserviceId() {
        return valueAddedserviceId;
    }

    public void setValueAddedserviceId(String valueAddedserviceId) {
        this.valueAddedserviceId = valueAddedserviceId;
    }

    public String getValueAddedserviceTitle() {
        return valueAddedserviceTitle;
    }

    public void setValueAddedserviceTitle(String valueAddedserviceTitle) {
        this.valueAddedserviceTitle = valueAddedserviceTitle;
    }

    public String getValueAddedserviceVender() {
        return valueAddedserviceVender;
    }

    public void setValueAddedserviceVender(String valueAddedserviceVender) {
        this.valueAddedserviceVender = valueAddedserviceVender;
    }

    public String getValueAddedserviceAttachment() {
        return valueAddedserviceAttachment;
    }

    public void setValueAddedserviceAttachment(String valueAddedserviceAttachment) {
        this.valueAddedserviceAttachment = valueAddedserviceAttachment;
    }

}