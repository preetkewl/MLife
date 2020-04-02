package com.mlife.web.holder.Response;

import com.mlife.web.model.AmentieeListResponse;

import java.util.Observable;

/**
 * Created by milagro on 10/7/2017.
 */

public class ObjectAmentieeList extends Observable {


    AmentieeListResponse amentieeListResponse = new AmentieeListResponse();

    public AmentieeListResponse getAmentieeListResponse() {
        return amentieeListResponse;
    }

    public void setAmentieeListResponse(AmentieeListResponse amentieeListResponse) {
        this.amentieeListResponse = amentieeListResponse;
        setChanged();
        notifyObservers();
    }
}