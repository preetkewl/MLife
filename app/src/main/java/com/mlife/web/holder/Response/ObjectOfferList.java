package com.mlife.web.holder.Response;

import com.mlife.web.model.OfferListResponse;

import java.util.Observable;

/**
 * Created by milagro on 10/9/2017.
 */

public class ObjectOfferList extends Observable {

    OfferListResponse offerListResponse = new OfferListResponse();

    public OfferListResponse getOfferListResponse() {
        return offerListResponse;
    }

    public void setOfferListResponse(OfferListResponse offerListResponse) {
        this.offerListResponse = offerListResponse;
        setChanged();
        notifyObservers();
    }

}
