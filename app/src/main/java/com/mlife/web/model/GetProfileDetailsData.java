package com.mlife.web.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by milagro on 12/26/2017.
 */

public class GetProfileDetailsData {


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
    @SerializedName("isPublic")
    @Expose
    private String isPublic;
    @SerializedName("imagePath")
    @Expose
    private String imagePath;
    @SerializedName("homeTown")
    @Expose
    private String homeTown;
    @SerializedName("livesIn")
    @Expose
    private String livesIn;
    @SerializedName("dateOfBirth")
    @Expose
    private String dateOfBirth;
    @SerializedName("marriageAnniversary")
    @Expose
    private String marriageAnniversary;
    @SerializedName("interest")
    @Expose
    private String interest;
    @SerializedName("companyType")
    @Expose
    private String companyType;
    @SerializedName("companyLocation")
    @Expose
    private String companyLocation;
    @SerializedName("education")
    @Expose
    private List<GetProfileDetailsDetails> education = null;

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

    public String getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(String isPublic) {
        this.isPublic = isPublic;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getHomeTown() {
        return homeTown;
    }

    public void setHomeTown(String homeTown) {
        this.homeTown = homeTown;
    }

    public String getLivesIn() {
        return livesIn;
    }

    public void setLivesIn(String livesIn) {
        this.livesIn = livesIn;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getMarriageAnniversary() {
        return marriageAnniversary;
    }

    public void setMarriageAnniversary(String marriageAnniversary) {
        this.marriageAnniversary = marriageAnniversary;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public String getCompanyLocation() {
        return companyLocation;
    }

    public void setCompanyLocation(String companyLocation) {
        this.companyLocation = companyLocation;
    }

    public List<GetProfileDetailsDetails> getEducation() {
        return education;
    }

    public void setEducation(List<GetProfileDetailsDetails> education) {
        this.education = education;
    }
}