package com.mlife.web.holder.Response;

import com.mlife.web.model.LoadSiteVisitsResponse;

import java.util.Observable;

/**
 * Created by milagro on 9/30/2017.
 */

public class ObjectLoadSiteVisits extends Observable {

    LoadSiteVisitsResponse loadSiteVisitsResponse = new LoadSiteVisitsResponse();

    public LoadSiteVisitsResponse getLoadSiteVisitsResponse() {
        return loadSiteVisitsResponse;
    }

    public void setLoadSiteVisitsResponse(LoadSiteVisitsResponse loadSiteVisitsResponse) {
        this.loadSiteVisitsResponse = loadSiteVisitsResponse;
        setChanged();
        notifyObservers();
    }
}
