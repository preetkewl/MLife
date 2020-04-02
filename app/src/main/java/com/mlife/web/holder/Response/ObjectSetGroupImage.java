package com.mlife.web.holder.Response;

import com.mlife.web.model.SetGroupImageResponse;

import java.util.Observable;

/**
 * Created by milagro on 10/12/2017.
 */

public class ObjectSetGroupImage extends Observable {

    SetGroupImageResponse setGroupImageResponse = new SetGroupImageResponse();

    public SetGroupImageResponse getSetGroupImageResponse() {
        return setGroupImageResponse;
    }

    public void setSetGroupImageResponse(SetGroupImageResponse setGroupImageResponse) {
        this.setGroupImageResponse = setGroupImageResponse;
        setChanged();
        notifyObservers();
    }
}
