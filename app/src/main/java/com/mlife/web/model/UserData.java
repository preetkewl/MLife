package com.mlife.web.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by milagro on 9/19/2017.
 */

public class UserData implements Serializable {

    @SerializedName("userFullName")
    @Expose
    private String userFullName;

    @SerializedName("userEmail")
    @Expose
    private String userEmail;

    @SerializedName("userId")
    @Expose
    private String userId;

    @SerializedName("token")
    @Expose
    private String token;

    @SerializedName("notification")
    @Expose
    private String notification;

    @SerializedName("sound")
    @Expose
    private String sound;

    @SerializedName("mobile")
    @Expose
    private String mobile;

    @SerializedName("imagePath")
    @Expose
    private String imagePath;

    @SerializedName("properties")
    @Expose
    private List<PropertyDetails> properties = null;

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public List<PropertyDetails> getProperties() {
        return properties;
    }

    public void setProperties(List<PropertyDetails> properties) {
        this.properties = properties;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}