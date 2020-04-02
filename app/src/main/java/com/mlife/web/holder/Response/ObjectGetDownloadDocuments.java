package com.mlife.web.holder.Response;

import com.mlife.web.model.GetDownloadDocumentsResponse;

import java.util.Observable;

/**
 * Created by milagro on 11/6/2017.
 */

public class ObjectGetDownloadDocuments extends Observable {

    GetDownloadDocumentsResponse getDownloadDocumentsResponse = new GetDownloadDocumentsResponse();

    public GetDownloadDocumentsResponse getGetDownloadDocumentsResponse() {
        return getDownloadDocumentsResponse;
    }

    public void setGetDownloadDocumentsResponse(GetDownloadDocumentsResponse getDownloadDocumentsResponse) {
        this.getDownloadDocumentsResponse = getDownloadDocumentsResponse;
        setChanged();
        notifyObservers();
    }

}