package com.mlife.web.holder.Response;

import com.mlife.web.model.GetServiceRequestsResponse;

import java.util.Observable;

/**
 * Created by milagro on 10/14/2017.
 */

public class ObjectGetServiceRequests extends Observable{

    GetServiceRequestsResponse getServiceRequestsResponse = new GetServiceRequestsResponse();

    public GetServiceRequestsResponse getGetServiceRequestsResponse() {
        return getServiceRequestsResponse;
    }

    public void setGetServiceRequestsResponse(GetServiceRequestsResponse getServiceRequestsResponse) {
        this.getServiceRequestsResponse = getServiceRequestsResponse;
        setChanged();
        notifyObservers();
    }
}
