package com.mlife.web.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by milagro on 11/7/2017.
 */

public class ProjectConstructionUpdateDetails {

    @SerializedName("Image")
    @Expose
    private String image;

    @SerializedName("Date")
    @Expose
    private String date;

    @SerializedName("Title")
    @Expose
    private String Title;

    @SerializedName("Description")
    @Expose
    private String Description;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}