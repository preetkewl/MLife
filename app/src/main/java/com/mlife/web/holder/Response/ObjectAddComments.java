package com.mlife.web.holder.Response;

import com.mlife.web.model.AddCommentsResponse;

import java.util.Observable;

/**
 * Created by milagro on 10/5/2017.
 */

public class ObjectAddComments  extends Observable {

    AddCommentsResponse addCommentsResponse = new AddCommentsResponse();

    public AddCommentsResponse getAddCommentsResponse() {
        return addCommentsResponse;
    }

    public void setAddCommentsResponse(AddCommentsResponse addCommentsResponse) {
        this.addCommentsResponse = addCommentsResponse;
        setChanged();
        notifyObservers();
    }
}
