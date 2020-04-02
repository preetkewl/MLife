package com.mlife.web.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by milagro on 10/14/2017.
 */

public class CommitteeManagmentListData {

    @SerializedName("committee_managment_name")
    @Expose
    private String committeeManagmentName;

    @SerializedName("committee_managment_designation")
    @Expose
    private String committeeManagmentDesignation;

    public String getCommitteeManagmentName() {
        return committeeManagmentName;
    }

    public void setCommitteeManagmentName(String committeeManagmentName) {
        this.committeeManagmentName = committeeManagmentName;
    }

    public String getCommitteeManagmentDesignation() {
        return committeeManagmentDesignation;
    }

    public void setCommitteeManagmentDesignation(String committeeManagmentDesignation) {
        this.committeeManagmentDesignation = committeeManagmentDesignation;
    }

}