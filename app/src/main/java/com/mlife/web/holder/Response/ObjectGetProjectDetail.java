package com.mlife.web.holder.Response;

import com.mlife.web.model.GetProjectDetailResponse;

import java.util.Observable;

/**
 * Created by milagro on 10/10/2017.
 */

public class ObjectGetProjectDetail  extends Observable {

    GetProjectDetailResponse  getProjectDetailResponse = new GetProjectDetailResponse();

    public GetProjectDetailResponse getGetProjectDetailResponse() {
        return getProjectDetailResponse;
    }

    public void setGetProjectDetailResponse(GetProjectDetailResponse getProjectDetailResponse) {
        this.getProjectDetailResponse = getProjectDetailResponse;
        setChanged();
        notifyObservers();
    }

}