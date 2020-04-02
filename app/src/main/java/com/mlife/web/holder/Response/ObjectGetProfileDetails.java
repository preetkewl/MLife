package com.mlife.web.holder.Response;

import com.mlife.web.model.GetProfileDetailsResponse;

import java.io.Serializable;
import java.util.Observable;

/**
 * Created by milagro on 12/26/2017.
 */

public class ObjectGetProfileDetails extends Observable implements Serializable{

    GetProfileDetailsResponse getProfileDetailsResponse = new GetProfileDetailsResponse();

    public GetProfileDetailsResponse getGetProfileDetailsResponse() {
        return getProfileDetailsResponse;
    }

    public void setGetProfileDetailsResponse(GetProfileDetailsResponse getProfileDetailsResponse) {
        this.getProfileDetailsResponse = getProfileDetailsResponse;
        setChanged();
        notifyObservers();
    }
}
