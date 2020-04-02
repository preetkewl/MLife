package com.mlife.web.holder.Response;

import com.mlife.web.model.ResidentListResponse;

import java.util.Observable;

/**
 * Created by milagro on 10/14/2017.
 */

public class ObjectResidentList extends Observable {

    ResidentListResponse residentListResponse = new ResidentListResponse();

    public ResidentListResponse getResidentListResponse() {
        return residentListResponse;
    }

    public void setResidentListResponse(ResidentListResponse residentListResponse) {
        this.residentListResponse = residentListResponse;
        setChanged();
        notifyObservers();
    }
}
