package com.mlife.web.holder.Response;

import com.mlife.web.model.CancelSiteVisitResponse;

import java.util.Observable;

/**
 * Created by milagro on 10/3/2017.
 */

public class ObjectCancelSiteVisit extends Observable {

    CancelSiteVisitResponse cancelSiteVisitResponse = new CancelSiteVisitResponse();

    public CancelSiteVisitResponse getCancelSiteVisitResponse() {
        return cancelSiteVisitResponse;
    }

    public void setCancelSiteVisitResponse(CancelSiteVisitResponse cancelSiteVisitResponse) {
        this.cancelSiteVisitResponse = cancelSiteVisitResponse;
        setChanged();
        notifyObservers();
    }
}