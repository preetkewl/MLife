package com.mlife.adapter;

/**
 * Created by milagro on 8/21/2017.
 */

public class OurProjectsGetterSetter {

    String Name, Location, Typology, Price;
    int Image;

    public OurProjectsGetterSetter(String name, String location, String typology, String price, int image) {
        Name = name;
        Location = location;
        Typology = typology;
        Price = price;
        Image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getTypology() {
        return Typology;
    }

    public void setTypology(String typology) {
        Typology = typology;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

}