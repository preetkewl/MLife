package com.mlife.web.holder.Response;

import com.mlife.web.model.LoadCommentsResponse;

import java.util.Observable;

/**
 * Created by milagro on 10/5/2017.
 */

public class ObjectLoadComments extends Observable {

    LoadCommentsResponse loadCommentsResponse = new LoadCommentsResponse();

    public LoadCommentsResponse getLoadCommentsResponse() {
        return loadCommentsResponse;
    }

    public void setLoadCommentsResponse(LoadCommentsResponse loadCommentsResponse) {
        this.loadCommentsResponse = loadCommentsResponse;
        setChanged();
        notifyObservers();
    }
}
