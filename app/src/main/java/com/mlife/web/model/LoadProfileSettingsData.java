package com.mlife.web.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by milagro on 1/15/2018.
 */

public class LoadProfileSettingsData {

    @SerializedName("educationalQualification")
    @Expose
    private List<LoadProfileSettingsEducation> educationalQualification = null;

    @SerializedName("ocupation")
    @Expose
    private List<LoadProfileSettingsOccupation> ocupation = null;

    @SerializedName("interests")
    @Expose
    private List<LoadProfileSettingsIntrest> interests = null;

    public List<LoadProfileSettingsEducation> getEducationalQualification() {
        return educationalQualification;
    }

    public void setEducationalQualification(List<LoadProfileSettingsEducation> educationalQualification) {
        this.educationalQualification = educationalQualification;
    }

    public List<LoadProfileSettingsOccupation> getOcupation() {
        return ocupation;
    }

    public void setOcupation(List<LoadProfileSettingsOccupation> ocupation) {
        this.ocupation = ocupation;
    }

    public List<LoadProfileSettingsIntrest> getInterests() {
        return interests;
    }

    public void setInterests(List<LoadProfileSettingsIntrest> interests) {
        this.interests = interests;
    }

}