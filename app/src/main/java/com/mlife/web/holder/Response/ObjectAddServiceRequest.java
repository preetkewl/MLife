package com.mlife.web.holder.Response;

import com.mlife.web.model.AddServiceRequestResponse;

import java.util.Observable;

/**
 * Created by milagro on 10/26/2017.
 */

public class ObjectAddServiceRequest extends Observable{

    AddServiceRequestResponse addServiceRequestResponse = new AddServiceRequestResponse();

    public AddServiceRequestResponse getAddServiceRequestResponse() {
        return addServiceRequestResponse;
    }

    public void setAddServiceRequestResponse(AddServiceRequestResponse addServiceRequestResponse) {
        this.addServiceRequestResponse = addServiceRequestResponse;

        setChanged();
        notifyObservers();
    }
}
