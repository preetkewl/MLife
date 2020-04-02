package com.mlife.web.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by milagro on 1/29/2018.
 */

public class ResidentListDetails {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("hasProfile")
    @Expose
    private Boolean hasProfile;
    @SerializedName("profileId")
    @Expose
    private String profileId;
    @SerializedName("tower")
    @Expose
    private String tower;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getHasProfile() {
        return hasProfile;
    }

    public void setHasProfile(Boolean hasProfile) {
        this.hasProfile = hasProfile;
    }

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    public String getTower() {
        return tower;
    }

    public void setTower(String tower) {
        this.tower = tower;
    }
}