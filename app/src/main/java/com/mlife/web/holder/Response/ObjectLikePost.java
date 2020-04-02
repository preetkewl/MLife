package com.mlife.web.holder.Response;

import com.mlife.web.model.LikePostResponse;

import java.util.Observable;

/**
 * Created by milagro on 10/5/2017.
 */

public class ObjectLikePost extends Observable {

    LikePostResponse likePostResponse = new LikePostResponse();

    public LikePostResponse getLikePostResponse() {
        return likePostResponse;
    }

    public void setLikePostResponse(LikePostResponse likePostResponse) {
        this.likePostResponse = likePostResponse;
        setChanged();
        notifyObservers();
    }
}
