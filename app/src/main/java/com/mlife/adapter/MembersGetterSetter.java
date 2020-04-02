package com.mlife.adapter;

/**
 * Created by milagro on 9/7/2017.
 */

public class MembersGetterSetter {

    int id, Image;
    String Name;

    public MembersGetterSetter(int id, int image, String name) {
        this.id = id;
        Image = image;
        Name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

}