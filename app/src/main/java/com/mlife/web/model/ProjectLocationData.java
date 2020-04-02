package com.mlife.web.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by milagro on 10/10/2017.
 */

public class ProjectLocationData {

    @SerializedName("mldl_project_id")
    @Expose
    private String mldlProjectId;
    @SerializedName("mldl_project_location")
    @Expose
    private String mldlProjectLocation;
    @SerializedName("mldl_project_location_latitude")
    @Expose
    private String mldlProjectLocationLatitude;
    @SerializedName("mldl_project_location_longitude")
    @Expose
    private String mldlProjectLocationLongitude;

    public String getMldlProjectId() {
        return mldlProjectId;
    }

    public void setMldlProjectId(String mldlProjectId) {
        this.mldlProjectId = mldlProjectId;
    }

    public String getMldlProjectLocation() {
        return mldlProjectLocation;
    }

    public void setMldlProjectLocation(String mldlProjectLocation) {
        this.mldlProjectLocation = mldlProjectLocation;
    }

    public String getMldlProjectLocationLatitude() {
        return mldlProjectLocationLatitude;
    }

    public void setMldlProjectLocationLatitude(String mldlProjectLocationLatitude) {
        this.mldlProjectLocationLatitude = mldlProjectLocationLatitude;
    }

    public String getMldlProjectLocationLongitude() {
        return mldlProjectLocationLongitude;
    }

    public void setMldlProjectLocationLongitude(String mldlProjectLocationLongitude) {
        this.mldlProjectLocationLongitude = mldlProjectLocationLongitude;
    }

}