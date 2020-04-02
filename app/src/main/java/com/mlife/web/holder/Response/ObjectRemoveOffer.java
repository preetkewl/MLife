package com.mlife.web.holder.Response;

import com.mlife.web.model.RemoveOfferResponse;

import java.util.Observable;

/**
 * Created by milagro on 3/1/2018.
 */

public class ObjectRemoveOffer extends Observable {

    RemoveOfferResponse removeOfferResponse = new RemoveOfferResponse();

    public RemoveOfferResponse getRemoveOfferResponse() {
        return removeOfferResponse;
    }

    public void setRemoveOfferResponse(RemoveOfferResponse removeOfferResponse) {
        this.removeOfferResponse = removeOfferResponse;
        setChanged();
        notifyObservers();
    }
}
