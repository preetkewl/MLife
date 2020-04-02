package com.mlife.web.holder.Response;

import com.mlife.web.model.GetPostsResponse;

import java.util.Observable;

/**
 * Created by milagro on 10/6/2017.
 */

public class ObjectGetPosts extends Observable {

    GetPostsResponse getPostsResponse = new GetPostsResponse();

    public GetPostsResponse getGetPostsResponse() {
        return getPostsResponse;
    }

    public void setGetPostsResponse(GetPostsResponse getPostsResponse) {
        this.getPostsResponse = getPostsResponse;
        setChanged();
        notifyObservers();
    }

}
