package com.mlife.web.holder.Response;

import com.mlife.web.model.CreatePostResponse;

import java.util.Observable;

/**
 * Created by milagro on 10/5/2017.
 */

public class ObjectCreatePost extends Observable {

    CreatePostResponse createPostResponse = new CreatePostResponse();

    public CreatePostResponse getCreatePostResponse() {
        return createPostResponse;
    }

    public void setCreatePostResponse(CreatePostResponse createPostResponse) {
        this.createPostResponse = createPostResponse;
        setChanged();
        notifyObservers();
    }
}