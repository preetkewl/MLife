package com.mlife.adapter;

/**
 * Created by milagro on 8/11/2017.
 */

public class NewProjectLaunchGetterSetter {

    String Heading, Title;
    int Image;

    public NewProjectLaunchGetterSetter(String heading, String title, int image) {
        Heading = heading;
        Title = title;
        Image = image;
    }

    public String getHeading() {
        return Heading;
    }

    public void setHeading(String heading) {
        Heading = heading;
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
