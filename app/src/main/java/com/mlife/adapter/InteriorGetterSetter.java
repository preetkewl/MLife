package com.mlife.adapter;

/**
 * Created by milagro on 9/5/2017.
 */

public class InteriorGetterSetter {

    int id;
    String Name, Detail, Address, Phone, Email;

    public InteriorGetterSetter(int id, String name, String detail, String address, String phone, String email) {
        this.id = id;
        Name = name;
        Detail = detail;
        Address = address;
        Phone = phone;
        Email = email;
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

    public String getDetail() {
        return Detail;
    }

    public void setDetail(String detail) {
        Detail = detail;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}

