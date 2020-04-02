package com.mlife.web.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by milagro on 10/10/2017.
 */

public class GetProjectDetailData  {

    @SerializedName("mldl_project_id")
    @Expose
    private String mldlProjectId;

    @SerializedName("mldl_project_name")
    @Expose
    private String mldlProjectName;

    @SerializedName("mldl_project_desc")
    @Expose
    private String mldlProjectDesc;

    @SerializedName("mldl_project_city")
    @Expose
    private String mldlProjectCity;

    @SerializedName("banners")
    @Expose
    private List<String> banners = null;

    public String getMldlProjectId() {
        return mldlProjectId;
    }

    public void setMldlProjectId(String mldlProjectId) {
        this.mldlProjectId = mldlProjectId;
    }

    public String getMldlProjectName() {
        return mldlProjectName;
    }

    public void setMldlProjectName(String mldlProjectName) {
        this.mldlProjectName = mldlProjectName;
    }

    public String getMldlProjectDesc() {
        return mldlProjectDesc;
    }

    public void setMldlProjectDesc(String mldlProjectDesc) {
        this.mldlProjectDesc = mldlProjectDesc;
    }

    public String getMldlProjectCity() {
        return mldlProjectCity;
    }

    public void setMldlProjectCity(String mldlProjectCity) {
        this.mldlProjectCity = mldlProjectCity;
    }

    public List<String> getBanners() {
        return banners;
    }

    public void setBanners(List<String> banners) {
        this.banners = banners;
    }

}