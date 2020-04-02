package com.mlife.adapter;

/**
 * Created by milagro on 8/22/2017.
 */

public class OffersGetterSetter {

    int Image;
    String Heading, Details, Location;


    public OffersGetterSetter(int image, String heading, String location, String details) {
        Image = image;
        Heading = heading;
        Details = details;
        Location = location;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    public String getHeading() {
        return Heading;
    }

    public void setHeading(String heading) {
        Heading = heading;
    }

    public String getDetails() {
        return Details;
    }

    public void setDetails(String details) {
        Details = details;
    }

}