package com.mlife.web.holder.Response;

import com.mlife.web.model.UpdatePostResponse;

import java.util.Observable;

/**
 * Created by milagro on 11/15/2017.
 */

public class ObjectUpdatePost extends Observable {

    UpdatePostResponse updatePostResponse = new UpdatePostResponse();

    public UpdatePostResponse getUpdatePostResponse() {
        return updatePostResponse;
    }

    public void setUpdatePostResponse(UpdatePostResponse updatePostResponse) {
        this.updatePostResponse = updatePostResponse;
        setChanged();
        notifyObservers();
    }

}