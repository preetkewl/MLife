package com.mlife.web.holder.Response;

import com.mlife.web.model.ViewProfileDetailsResponse;
import java.util.Observable;

/**
 * Created by milagro on 1/4/2018.
 */

public class ObjectViewProfileDetails extends Observable{

    ViewProfileDetailsResponse viewProfileDetailsResponse = new ViewProfileDetailsResponse();

    public ViewProfileDetailsResponse getViewProfileDetailsResponse() {
        return viewProfileDetailsResponse;
    }

    public void setViewProfileDetailsResponse(ViewProfileDetailsResponse viewProfileDetailsResponse) {
        this.viewProfileDetailsResponse = viewProfileDetailsResponse;
        setChanged();
        notifyObservers();
    }

}