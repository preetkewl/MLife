package com.mlife.web.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by milagro on 11/8/2017.
 */

public class HomeBannerListData {

    @SerializedName("project_id")
    @Expose
    private String projectId;
    @SerializedName("banner")
    @Expose
    private String banner;
    @SerializedName("bannertype")
    @Expose
    private String bannertype;
    @SerializedName("bannerurl")
    @Expose
    private String bannerurl;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getBannertype() {
        return bannertype;
    }

    public void setBannertype(String bannertype) {
        this.bannertype = bannertype;
    }

    public String getBannerurl() {
        return bannerurl;
    }

    public void setBannerurl(String bannerurl) {
        this.bannerurl = bannerurl;
    }

}