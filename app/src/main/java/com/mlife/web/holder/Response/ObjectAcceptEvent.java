package com.mlife.web.holder.Response;

import com.mlife.web.model.AcceptEventResponse;

import java.util.Observable;

/**
 * Created by milagro on 10/5/2017.
 */

public class ObjectAcceptEvent extends Observable {

    AcceptEventResponse acceptEventResponse = new AcceptEventResponse();

    public AcceptEventResponse getAcceptEventResponse() {
        return acceptEventResponse;
    }

    public void setAcceptEventResponse(AcceptEventResponse acceptEventResponse) {
        this.acceptEventResponse = acceptEventResponse;
        setChanged();
        notifyObservers();
    }

}
