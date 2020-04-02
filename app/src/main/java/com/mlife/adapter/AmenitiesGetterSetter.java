package com.mlife.adapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by milagro on 10/10/2017.
 */

public class AmenitiesGetterSetter {

    List<String> image = new ArrayList<>();
    List<String> detail = new ArrayList<>();

    public AmenitiesGetterSetter(List<String> image, List<String> detail) {
        this.image = image;
        this.detail = detail;
    }

    public List<String> getImage() {
        return image;
    }

    public void setImage(List<String> image) {
        this.image = image;
    }

    public List<String> getDetail() {
        return detail;
    }

    public void setDetail(List<String> detail) {
        this.detail = detail;
    }

}