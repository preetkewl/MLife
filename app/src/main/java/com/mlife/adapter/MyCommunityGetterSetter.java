package com.mlife.adapter;

public class MyCommunityGetterSetter {

    String Title;
    int icon;

    public MyCommunityGetterSetter(String title, int icon) {
        Title = title;
        this.icon = icon;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

}