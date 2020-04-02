package com.mlife.web.holder.Response;

import com.mlife.web.model.GetKeyResponse;

import java.util.Observable;

/**
 * Created by milagro on 2/19/2018.
 */

public class ObjectGetKey extends Observable{

    GetKeyResponse getKeyResponse = new GetKeyResponse();

    public GetKeyResponse getGetKeyResponse() {
        return getKeyResponse;
    }

    public void setGetKeyResponse(GetKeyResponse getKeyResponse) {
        this.getKeyResponse = getKeyResponse;
        setChanged();
        notifyObservers();
    }
}
