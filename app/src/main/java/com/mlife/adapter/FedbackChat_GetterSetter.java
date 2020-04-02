package com.mlife.adapter;

/**
 * Created by milagro on 8/30/2017.
 */

public class FedbackChat_GetterSetter {
    String Name, Date, Message;

    public FedbackChat_GetterSetter(String name, String date, String message) {
        Name = name;
        Date = date;
        Message = message;
    }


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}
