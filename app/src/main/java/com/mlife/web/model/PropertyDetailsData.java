package com.mlife.web.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by milagro on 9/22/2017.
 */

public class PropertyDetailsData  {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("unitType")
    @Expose
    private String unitType;
    @SerializedName("houseNo")
    @Expose
    private String houseNo;
    @SerializedName("area")
    @Expose
    private String area;
    @SerializedName("tower")
    @Expose
    private String tower;
    @SerializedName("pDate")
    @Expose
    private Object pDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getTower() {
        return tower;
    }

    public void setTower(String tower) {
        this.tower = tower;
    }

    public Object getPDate() {
        return pDate;
    }

    public void setPDate(Object pDate) {
        this.pDate = pDate;
    }

}