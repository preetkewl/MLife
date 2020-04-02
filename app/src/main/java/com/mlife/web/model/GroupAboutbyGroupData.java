package com.mlife.web.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by milagro on 10/12/2017.
 */

public class GroupAboutbyGroupData {

    @SerializedName("creationdate")
    @Expose
    private String creationdate;
    @SerializedName("aboutus")
    @Expose
    private String aboutus;
    @SerializedName("moderater")
    @Expose
    private String moderater;
    @SerializedName("lastactivity")
    @Expose
    private String lastactivity;

    public String getCreationdate() {
        return creationdate;
    }

    public void setCreationdate(String creationdate) {
        this.creationdate = creationdate;
    }

    public String getAboutus() {
        return aboutus;
    }

    public void setAboutus(String aboutus) {
        this.aboutus = aboutus;
    }

    public String getModerater() {
        return moderater;
    }

    public void setModerater(String moderater) {
        this.moderater = moderater;
    }

    public String getLastactivity() {
        return lastactivity;
    }

    public void setLastactivity(String lastactivity) {
        this.lastactivity = lastactivity;
    }

}