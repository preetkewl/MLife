package com.mlife.web.holder.Response;

import com.mlife.web.model.ChangeGroupAboutResponse;

import java.util.Observable;

/**
 * Created by milagro on 10/12/2017.
 */

public class ObjectChangeGroupAbout extends Observable {

    ChangeGroupAboutResponse changeGroupAboutResponse = new ChangeGroupAboutResponse();

    public ChangeGroupAboutResponse getChangeGroupAboutResponse() {
        return changeGroupAboutResponse;
    }

    public void setChangeGroupAboutResponse(ChangeGroupAboutResponse changeGroupAboutResponse) {
        this.changeGroupAboutResponse = changeGroupAboutResponse;
        setChanged();
        notifyObservers();
    }
}
