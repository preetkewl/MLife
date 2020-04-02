package com.mlife.web.holder.Response;

import com.mlife.web.model.MyGroupsResponse;

import java.util.Observable;

/**
 * Created by milagro on 10/4/2017.
 */

public class ObjectMyGroups extends Observable {

    MyGroupsResponse myGroupsResponse = new MyGroupsResponse();

    public MyGroupsResponse getMyGroupsResponse() {
        return myGroupsResponse;
    }

    public void setMyGroupsResponse(MyGroupsResponse myGroupsResponse) {
        this.myGroupsResponse = myGroupsResponse;
        setChanged();
        notifyObservers();
    }
}
