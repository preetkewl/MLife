package com.mlife.web.holder.Response;

import com.mlife.web.model.AddCallBackResponse;

import java.util.Observable;

/**
 * Created by milagro on 10/9/2017.
 */

public class ObjectAddCallBack extends Observable {

    AddCallBackResponse addCallBackResponse = new AddCallBackResponse();

    public AddCallBackResponse getAddCallBackResponse() {
        return addCallBackResponse;
    }

    public void setAddCallBackResponse(AddCallBackResponse addCallBackResponse) {
        this.addCallBackResponse = addCallBackResponse;
        setChanged();
        notifyObservers();
    }
}
