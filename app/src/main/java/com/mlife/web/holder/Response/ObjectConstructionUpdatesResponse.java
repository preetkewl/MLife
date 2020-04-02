package com.mlife.web.holder.Response;

import com.mlife.web.model.ConstructionResponse;

import java.util.Observable;

/**
 * Created by milagro on 9/28/2017.
 */

public class ObjectConstructionUpdatesResponse extends Observable {

    ConstructionResponse constructionResponse = new ConstructionResponse();

    public ConstructionResponse getConstructionResponse() {
        return constructionResponse;
    }

    public void setConstructionResponse(ConstructionResponse constructionResponse) {
        this.constructionResponse = constructionResponse;
        setChanged();
        notifyObservers();
    }
}
