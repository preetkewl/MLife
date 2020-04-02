package com.example.focpc.mahindralifespaces.ui.activities.select_contact;

/**
 * Created by foc pc on 06-12-2017.
 */

public class UserStatusItem {

    private int status;
    private String user_phone;
    private String user_name;

    public UserStatusItem(int status, String user_phone) {
        this.status = status;
        this.user_phone = user_phone;
    }

    public int getUser_status() {
        return status;
    }

    public void setUser_status(int status) {
        this.status = status;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
}
