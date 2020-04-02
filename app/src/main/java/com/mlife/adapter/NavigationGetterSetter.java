package com.mlife.adapter;

/**
 * Created by milagro on 8/9/2017.
 */

public class NavigationGetterSetter {

    String Title;
    int Image, Id;

    public NavigationGetterSetter(int id, String title, int image) {
        Title = title;
        Image = image;
        Id = id;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

}