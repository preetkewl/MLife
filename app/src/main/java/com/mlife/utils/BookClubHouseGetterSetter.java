package com.mlife.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by milagro on 10/7/2017.
 */

public class BookClubHouseGetterSetter {

    String amenty_id, amenty_name, amenty_is_multiple;

//    public BookClubHouseGetterSetter(String amenty_id, String amenty_name, List<String> timeslot) {
//        this.amenty_id = amenty_id;
//        this.amenty_name = amenty_name;
//        this.timeslot = timeslot;
//    }

    public BookClubHouseGetterSetter(String amenty_id, String amenty_name, String amenty_is_multiple, List<String> timeslot) {
        this.amenty_id = amenty_id;
        this.amenty_name = amenty_name;
        this.amenty_is_multiple = amenty_is_multiple;
        this.timeslot = timeslot;
    }

    List<String> timeslot = new ArrayList<>();

    public String getAmenty_is_multiple() {
        return amenty_is_multiple;
    }

    public void setAmenty_is_multiple(String amenty_is_multiple) {
        this.amenty_is_multiple = amenty_is_multiple;
    }

    public String getAmenty_id() {
        return amenty_id;
    }

    public void setAmenty_id(String amenty_id) {
        this.amenty_id = amenty_id;
    }

    public String getAmenty_name() {
        return amenty_name;
    }

    public void setAmenty_name(String amenty_name) {
        this.amenty_name = amenty_name;
    }

    public List<String> getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(List<String> timeslot) {
        this.timeslot = timeslot;
    }
}
