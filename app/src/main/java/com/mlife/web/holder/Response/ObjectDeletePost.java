package com.mlife.web.holder.Response;

import com.mlife.web.model.DeletePostResponse;

import java.util.Observable;

/**
 * Created by milagro on 10/16/2017.
 */

public class ObjectDeletePost extends Observable {

    DeletePostResponse deletePostResponse = new DeletePostResponse();

    public DeletePostResponse getDeletePostResponse() {
        return deletePostResponse;
    }

    public void setDeletePostResponse(DeletePostResponse deletePostResponse) {
        this.deletePostResponse = deletePostResponse;
        setChanged();
        notifyObservers();
    }

}
