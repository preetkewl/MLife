package com.mlife.web.holder.Response;

import com.mlife.web.model.ChangeGroupDetailsResponse;

import java.util.Observable;

/**
 * Created by milagro on 1/23/2018.
 */

public class ObjectChangeGroupDetails extends Observable {

    ChangeGroupDetailsResponse changeGroupDetailsResponse = new ChangeGroupDetailsResponse();

    public ChangeGroupDetailsResponse getChangeGroupDetailsResponse() {
        return changeGroupDetailsResponse;
    }

    public void setChangeGroupDetailsResponse(ChangeGroupDetailsResponse changeGroupDetailsResponse) {
        this.changeGroupDetailsResponse = changeGroupDetailsResponse;
        setChanged();
        notifyObservers();
    }
}