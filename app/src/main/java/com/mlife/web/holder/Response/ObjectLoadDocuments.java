package com.mlife.web.holder.Response;

import com.mlife.web.model.LoadDocumentsResponse;

import java.util.Observable;

/**
 * Created by milagro on 11/2/2017.
 */

public class ObjectLoadDocuments extends Observable{

    LoadDocumentsResponse loadDocumentsResponse = new LoadDocumentsResponse();

    public LoadDocumentsResponse getLoadDocumentsResponse() {
        return loadDocumentsResponse;
    }

    public void setLoadDocumentsResponse(LoadDocumentsResponse loadDocumentsResponse) {
        this.loadDocumentsResponse = loadDocumentsResponse;
        setChanged();
        notifyObservers();
    }

}