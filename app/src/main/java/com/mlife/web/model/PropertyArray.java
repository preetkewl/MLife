package com.mlife.web.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by milagro on 9/21/2017.
 */

public class PropertyArray {

    @SerializedName("properties")
    @Expose
    private List<PropertyDetails> properties = null;

    public List<PropertyDetails> getProperties() {
        return properties;
    }

    public void setProperties(List<PropertyDetails> properties) {
        this.properties = properties;
    }

}