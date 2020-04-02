package com.mlife.web.holder.Response;

import com.mlife.web.model.GetProductResponse;

import java.util.Observable;

/**
 * Created by milagro on 11/17/2017.
 */

public class ObjectGetProduct extends Observable {

    GetProductResponse getProductResponse = new GetProductResponse();

    public GetProductResponse getGetProductResponse() {
        return getProductResponse;
    }

    public void setGetProductResponse(GetProductResponse getProductResponse) {
        this.getProductResponse = getProductResponse;
        setChanged();
        notifyObservers();
    }

}
