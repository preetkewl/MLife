package com.mlife.web.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by milagro on 10/10/2017.
 */

public class ProjectGalleryData {

    @SerializedName("mldl_project_id")
    @Expose
    private String mldlProjectId;

    @SerializedName("gallery")
    @Expose
    private List<Gallery> gallery = null;

    public String getMldlProjectId() {
        return mldlProjectId;
    }

    public void setMldlProjectId(String mldlProjectId) {
        this.mldlProjectId = mldlProjectId;
    }

    public List<Gallery> getGallery() {
        return gallery;
    }

    public void setGallery(List<Gallery> gallery) {
        this.gallery = gallery;
    }

}