package com.mlife.web.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by milagro on 9/28/2017.
 */

public class ConstructionArray {

    @SerializedName("details")
    @Expose
    private List<ConstructionData> details = null;

    @SerializedName("mldl_project_constructionupdate_desclaimer")
    @Expose
    private String mldl_project_constructionupdate_desclaimer;

    public String getMldl_project_constructionupdate_desclaimer() {
        return mldl_project_constructionupdate_desclaimer;
    }

    public void setMldl_project_constructionupdate_desclaimer(String mldl_project_constructionupdate_desclaimer) {
        this.mldl_project_constructionupdate_desclaimer = mldl_project_constructionupdate_desclaimer;
    }


    public List<ConstructionData> getDetails() {
        return details;
    }

    public void setDetails(List<ConstructionData> details) {
        this.details = details;
    }

}