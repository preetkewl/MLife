package com.mlife.adapter;

/**
 * Created by milagro on 8/29/2017.
 */

public class Contact {

    String name, phoneNumber;
    boolean isChecked;


    public Contact(String name, String phoneNumber, boolean isChecked) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.isChecked = isChecked;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public Contact(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
}
