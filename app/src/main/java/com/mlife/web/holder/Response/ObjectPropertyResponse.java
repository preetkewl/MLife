package com.mlife.web.holder.Response;

import com.mlife.web.model.PropertyResponse;

import java.util.Observable;

/**
 * Created by milagro on 9/21/2017.
 */

public class ObjectPropertyResponse extends Observable {

    PropertyResponse propertyResponse = new PropertyResponse();

    public PropertyResponse getPropertyResponse() {
        return propertyResponse;
    }

    public void setPropertyResponse(PropertyResponse propertyResponse) {
        this.propertyResponse = propertyResponse;
        setChanged();
        notifyObservers();
    }
}
