package com.mlife.web.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by milagro on 10/11/2017.
 */

public class projectOffersData {

    @SerializedName("mldl_project_id")
    @Expose
    private String mldlProjectId;
    @SerializedName("mldl_project_city")
    @Expose
    private String mldlProjectCity;
    @SerializedName("mldl_project_name")
    @Expose
    private String mldlProjectName;
    @SerializedName("mldl_project_offer")
    @Expose
    private List<projectOffersDetails> mldlProjectOffer = null;

    public String getMldlProjectId() {
        return mldlProjectId;
    }

    public void setMldlProjectId(String mldlProjectId) {
        this.mldlProjectId = mldlProjectId;
    }

    public String getMldlProjectCity() {
        return mldlProjectCity;
    }

    public void setMldlProjectCity(String mldlProjectCity) {
        this.mldlProjectCity = mldlProjectCity;
    }

    public String getMldlProjectName() {
        return mldlProjectName;
    }

    public void setMldlProjectName(String mldlProjectName) {
        this.mldlProjectName = mldlProjectName;
    }

    public List<projectOffersDetails> getMldlProjectOffer() {
        return mldlProjectOffer;
    }

    public void setMldlProjectOffer(List<projectOffersDetails> mldlProjectOffer) {
        this.mldlProjectOffer = mldlProjectOffer;
    }

}