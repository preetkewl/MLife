package com.mlife.web.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by milagro on 11/17/2017.
 */

public class GetProductData {

    @SerializedName("vas_product_id")
    @Expose
    private String vasProductId;
    @SerializedName("vas_product_name")
    @Expose
    private String vasProductName;

    public String getVasProductId() {
        return vasProductId;
    }

    public void setVasProductId(String vasProductId) {
        this.vasProductId = vasProductId;
    }

    public String getVasProductName() {
        return vasProductName;
    }

    public void setVasProductName(String vasProductName) {
        this.vasProductName = vasProductName;
    }

}