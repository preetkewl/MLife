package com.mlife.web.holder.Response;

import com.mlife.web.model.AllPropertyResponse;

import java.util.Observable;

/**
 * Created by milagro on 9/29/2017.
 */

public class ObjectAllProperty extends Observable {

    AllPropertyResponse allPropertyResponse = new AllPropertyResponse();

    public AllPropertyResponse getAllPropertyResponse() {
        return allPropertyResponse;
    }

    public void setAllPropertyResponse(AllPropertyResponse allPropertyResponse) {
        this.allPropertyResponse = allPropertyResponse;
        setChanged();
        notifyObservers();
    }
}