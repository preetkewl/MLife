package com.mlife.web.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by milagro on 10/7/2017.
 */

public class AmentieeListData  {

    @SerializedName("amenty_id")
    @Expose
    private String amentyId;

    @SerializedName("amenty_name")
    @Expose
    private String amentyName;

    @SerializedName("timeslot_time")
    @Expose
    private List<String> timeslotTime = null;

    @SerializedName("amenty_is_multiple")
    @Expose
    private String amenty_is_multiple ;

    public String getAmenty_is_multiple() {
        return amenty_is_multiple;
    }

    public void setAmenty_is_multiple(String amenty_is_multiple) {
        this.amenty_is_multiple = amenty_is_multiple;
    }

    public String getAmentyId() {
        return amentyId;
    }

    public void setAmentyId(String amentyId) {
        this.amentyId = amentyId;
    }

    public String getAmentyName() {
        return amentyName;
    }

    public void setAmentyName(String amentyName) {
        this.amentyName = amentyName;
    }

    public List<String> getTimeslotTime() {
        return timeslotTime;
    }

    public void setTimeslotTime(List<String> timeslotTime) {
        this.timeslotTime = timeslotTime;
    }

}