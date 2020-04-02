package com.mlife.web.holder.Response;

import com.mlife.web.model.ExporeGroupsResponse;

import java.util.Observable;

/**
 * Created by milagro on 10/3/2017.
 */

public class ObjectExploreGroups extends Observable {

    ExporeGroupsResponse exporeGroupsResponse = new ExporeGroupsResponse();

    public ExporeGroupsResponse getExporeGroupsResponse() {
        return exporeGroupsResponse;
    }

    public void setExporeGroupsResponse(ExporeGroupsResponse exporeGroupsResponse) {
        this.exporeGroupsResponse = exporeGroupsResponse;
        setChanged();
        notifyObservers();
    }

}
