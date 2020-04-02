package com.mlife.web.holder.Response;

import com.mlife.web.model.LoadGroupTypesResponse;

import java.util.Observable;

/**
 * Created by milagro on 9/30/2017.
 */

public class ObjectLoadGroupTypes extends Observable {
    LoadGroupTypesResponse loadGroupTypesResponse = new LoadGroupTypesResponse();

    public LoadGroupTypesResponse getLoadGroupTypesResponse() {
        return loadGroupTypesResponse;
    }

    public void setLoadGroupTypesResponse(LoadGroupTypesResponse loadGroupTypesResponse) {
        this.loadGroupTypesResponse = loadGroupTypesResponse;
        setChanged();
        notifyObservers();

    }
}
