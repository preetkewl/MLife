package com.mlife.adapter;

public class ClubhouseEventGetterSetter {

    int id;
    String Name, Date, Time, Count, Status;

    public ClubhouseEventGetterSetter(int id, String name, String date, String time, String count, String status) {
        this.id = id;
        Name = name;
        Date = date;
        Time = time;
        Count = count;
        Status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getCount() {
        return Count;
    }

    public void setCount(String count) {
        Count = count;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}