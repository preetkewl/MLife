package com.mlife.web.holder.Response;

import com.mlife.web.model.AddClassifiedResponse;

import java.util.Observable;

/**
 * Created by milagro on 10/9/2017.
 */

public class ObjectAddClassified  extends Observable {

    AddClassifiedResponse addClassifiedResponse = new AddClassifiedResponse();

    public AddClassifiedResponse getAddClassifiedResponse() {
        return addClassifiedResponse;
    }

    public void setAddClassifiedResponse(AddClassifiedResponse addClassifiedResponse) {
        this.addClassifiedResponse = addClassifiedResponse;
        setChanged();
        notifyObservers();
    }
}