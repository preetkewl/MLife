package com.mlife.adapter;

import java.io.Serializable;

/**
 * Created by milagro on 9/6/2017.
 */

public class ProjectImageGetterSetter implements Serializable{

    String Resource, Date, Image, caption, description;

    public ProjectImageGetterSetter(String resource, String date, String image, String caption, String description) {
        Resource = resource;
        Date = date;
        Image = image;
        this.caption = caption;
        this.description = description;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {return Image;}

    public void setImage(String image) {Image = image;}

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public ProjectImageGetterSetter(String resource) {
        Resource = resource;
    }

    public String getResource() {
        return Resource;
    }

    public void setResource(String resource) {
        Resource = resource;
    }
}