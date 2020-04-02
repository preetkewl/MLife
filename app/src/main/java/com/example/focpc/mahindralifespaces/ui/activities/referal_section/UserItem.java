package com.example.focpc.mahindralifespaces.ui.activities.referal_section;

/**
 * Created by foc pc on 14-12-2017.
 */

public class UserItem {

    private int user_id;
    private String user_name;
    private String user_email;
    private String user_phone;

    public UserItem(int user_id, String user_name, String user_email, String user_phone) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_email = user_email;
        this.user_phone = user_phone;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }


}
