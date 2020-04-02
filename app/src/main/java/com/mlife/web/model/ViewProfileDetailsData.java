package com.mlife.web.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by milagro on 1/4/2018.
 */

public class ViewProfileDetailsData {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("mobile")
    @Expose
    private String mobile;

    @SerializedName("companyName")
    @Expose
    private String companyName;

    @SerializedName("designation")
    @Expose
    private String designation;

    @SerializedName("imagePath")
    @Expose
    private String imagePath;

    @SerializedName("education")
    @Expose
    private List<ViewProfileDetailsEducationData> education = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public List<ViewProfileDetailsEducationData> getEducation() {
        return education;
    }

    public void setEducation(List<ViewProfileDetailsEducationData> education) {
        this.education = education;
    }

}