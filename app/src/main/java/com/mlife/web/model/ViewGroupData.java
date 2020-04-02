package com.mlife.web.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by milagro on 10/4/2017.
 */

public class ViewGroupData {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("imagePath")
    @Expose
    private Object imagePath;

    @SerializedName("hasJoined")
    @Expose
    private String hasJoined;

    @SerializedName("isOwner")
    @Expose
    private Boolean isOwner;

    @SerializedName("posts")
    @Expose
    private List<Object> posts = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getImagePath() {
        return imagePath;
    }

    public void setImagePath(Object imagePath) {
        this.imagePath = imagePath;
    }

    public String getHasJoined() {
        return hasJoined;
    }

    public void setHasJoined(String hasJoined) {
        this.hasJoined = hasJoined;
    }

    public Boolean getIsOwner() {
        return isOwner;
    }

    public void setIsOwner(Boolean isOwner) {
        this.isOwner = isOwner;
    }

    public List<Object> getPosts() {
        return posts;
    }

    public void setPosts(List<Object> posts) {
        this.posts = posts;
    }

}