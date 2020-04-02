package com.mlife.web.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by milagro on 10/3/2017.
 */

public class JoinGroupData {

    @SerializedName("properties")
    @Expose
    private List<Object> properties = null;

    public List<Object> getProperties() {
        return properties;
    }

    public void setProperties(List<Object> properties) {
        this.properties = properties;
    }

}