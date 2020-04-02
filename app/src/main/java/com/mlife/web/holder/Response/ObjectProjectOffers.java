package com.mlife.web.holder.Response;

import com.mlife.web.model.ProjectOffersResponse;

import java.util.Observable;

/**
 * Created by milagro on 10/11/2017.
 */

public class ObjectProjectOffers extends Observable {

    ProjectOffersResponse projectOffersResponse = new ProjectOffersResponse();

    public ProjectOffersResponse getProjectOffersResponse() {
        return projectOffersResponse;
    }

    public void setProjectOffersResponse(ProjectOffersResponse projectOffersResponse) {
        this.projectOffersResponse = projectOffersResponse;
        setChanged();
        notifyObservers();
    }
}
