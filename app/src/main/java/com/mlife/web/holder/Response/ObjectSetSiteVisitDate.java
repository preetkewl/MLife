package com.mlife.web.holder.Response;

import com.mlife.web.model.SetSiteVisitDateResponse;

import java.util.Observable;

/**
 * Created by milagro on 9/29/2017.
 */

public class ObjectSetSiteVisitDate  extends Observable {

    SetSiteVisitDateResponse setSiteVisitDateResponse = new SetSiteVisitDateResponse();

    public SetSiteVisitDateResponse getSetSiteVisitDateResponse() {
        return setSiteVisitDateResponse;
    }

    public void setSetSiteVisitDateResponse(SetSiteVisitDateResponse setSiteVisitDateResponse) {
        this.setSiteVisitDateResponse = setSiteVisitDateResponse;
        setChanged();
        notifyObservers();
    }
}
