package com.mlife.web.holder.Response;

import com.mlife.web.model.PropertyDetailResponse;

import java.util.Observable;

/**
 * Created by milagro on 9/26/2017.
 */

public class ObjectPropertyDetailsResponse extends Observable {


    PropertyDetailResponse propertyDetailResponse = new PropertyDetailResponse();

    public PropertyDetailResponse getPropertyDetailResponse() {
        return propertyDetailResponse;
    }

    public void setPropertyDetailResponse(PropertyDetailResponse propertyDetailResponse) {
        this.propertyDetailResponse = propertyDetailResponse;
        setChanged();
        notifyObservers();
    }

}