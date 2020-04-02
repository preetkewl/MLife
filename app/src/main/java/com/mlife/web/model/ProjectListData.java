package com.mlife.web.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by milagro on 10/10/2017.
 */

public class ProjectListData {

    @SerializedName("mldl_project_id")
    @Expose
    private String mldlProjectId;
    @SerializedName("mldl_project_name")
    @Expose
    private String mldlProjectName;
    @SerializedName("mldl_project_typology")
    @Expose
    private String mldlProjectTypology;
    @SerializedName("mldl_project_price")
    @Expose
    private String mldlProjectPrice;
    @SerializedName("mldl_project_city")
    @Expose
    private String mldlProjectCity;
    @SerializedName("amentiesname")
    @Expose
    private List<String> amentiesname = null;
    @SerializedName("amentiesshortname")
    @Expose
    private List<String> amentiesshortname = null;
    @SerializedName("amentiesimage")
    @Expose
    private List<String> amentiesimage = null;
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

    public String getMldlProjectTypology() {
        return mldlProjectTypology;
    }

    public void setMldlProjectTypology(String mldlProjectTypology) {
        this.mldlProjectTypology = mldlProjectTypology;
    }

    public String getMldlProjectPrice() {
        return mldlProjectPrice;
    }

    public void setMldlProjectPrice(String mldlProjectPrice) {
        this.mldlProjectPrice = mldlProjectPrice;
    }

    public String getMldlProjectCity() {
        return mldlProjectCity;
    }

    public void setMldlProjectCity(String mldlProjectCity) {
        this.mldlProjectCity = mldlProjectCity;
    }

    public List<String> getAmentiesname() {
        return amentiesname;
    }

    public void setAmentiesname(List<String> amentiesname) {
        this.amentiesname = amentiesname;
    }

    public List<String> getAmentiesshortname() {
        return amentiesshortname;
    }

    public void setAmentiesshortname(List<String> amentiesshortname) {
        this.amentiesshortname = amentiesshortname;
    }

    public List<String> getAmentiesimage() {
        return amentiesimage;
    }

    public void setAmentiesimage(List<String> amentiesimage) {
        this.amentiesimage = amentiesimage;
    }

    public List<String> getBanners() {
        return banners;
    }

    public void setBanners(List<String> banners) {
        this.banners = banners;
    }

}