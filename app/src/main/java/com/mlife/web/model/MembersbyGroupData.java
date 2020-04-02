package com.mlife.web.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by milagro on 10/12/2017.
 */

public class MembersbyGroupData {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("profileId")
    @Expose
    private String profileId;
    @SerializedName("imagePath")
    @Expose
    private String imagePath;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("requestId")
    @Expose
    private String requestId;
    @SerializedName("isOwner")
    @Expose
    private String isOwner;
    @SerializedName("canRemoveUsers")
    @Expose
    private String canRemoveUsers;

    public String getCanRemoveUsers() {
        return canRemoveUsers;
    }

    public void setCanRemoveUsers(String canRemoveUsers) {
        this.canRemoveUsers = canRemoveUsers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getIsOwner() {
        return isOwner;
    }

    public void setIsOwner(String isOwner) {
        this.isOwner = isOwner;
    }

}