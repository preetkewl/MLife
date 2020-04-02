package com.mlife.web.holder.Response;

import com.mlife.web.model.ChangeGroupNameResponse;

import java.util.Observable;

/**
 * Created by milagro on 10/12/2017.
 */

public class ObjectChangeGroupName extends Observable {
    ChangeGroupNameResponse changeGroupNameResponse = new ChangeGroupNameResponse();

    public ChangeGroupNameResponse getChangeGroupNameResponse() {
        return changeGroupNameResponse;
    }

    public void setChangeGroupNameResponse(ChangeGroupNameResponse changeGroupNameResponse) {
        this.changeGroupNameResponse = changeGroupNameResponse;
        setChanged();
        notifyObservers();

    }
}
