package com.mlife.web.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by milagro on 10/10/2017.
 */

public class ProjectConstructionUpdateData{

    @SerializedName("mldl_project_id")
    @Expose
    private String mldlProjectId;
    @SerializedName("mldl_project_constructionupdate_desclaimer")
    @Expose
    private String mldlProjectConstructionupdateDesclaimer;
    @SerializedName("construction")
    @Expose
    private List<ProjectConstructionUpdateDetails> construction = null;

    public String getMldlProjectId() {
        return mldlProjectId;
    }

    public void setMldlProjectId(String mldlProjectId) {
        this.mldlProjectId = mldlProjectId;
    }

    public String getMldlProjectConstructionupdateDesclaimer() {
        return mldlProjectConstructionupdateDesclaimer;
    }

    public void setMldlProjectConstructionupdateDesclaimer(String mldlProjectConstructionupdateDesclaimer) {
        this.mldlProjectConstructionupdateDesclaimer = mldlProjectConstructionupdateDesclaimer;
    }

    public List<ProjectConstructionUpdateDetails> getConstruction() {
        return construction;
    }

    public void setConstruction(List<ProjectConstructionUpdateDetails> construction) {
        this.construction = construction;
    }

}