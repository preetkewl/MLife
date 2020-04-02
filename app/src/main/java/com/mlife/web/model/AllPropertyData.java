package com.mlife.web.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by milagro on 9/29/2017.
 */

public class AllPropertyData  {

    @SerializedName("projectId")
    @Expose
    private String projectId;
    @SerializedName("name")
    @Expose
    private String name;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}