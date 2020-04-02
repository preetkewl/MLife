package com.mlife.web.holder.Response;

import com.mlife.web.model.JoinedGroupsResponse;

import java.util.Observable;

/**
 * Created by milagro on 10/4/2017.
 */

public class ObjectJoinedGrops extends Observable {
    JoinedGroupsResponse joinedGroupsResponse = new JoinedGroupsResponse();

    public JoinedGroupsResponse getJoinedGroupsResponse() {
        return joinedGroupsResponse;
    }

    public void setJoinedGroupsResponse(JoinedGroupsResponse joinedGroupsResponse) {
        this.joinedGroupsResponse = joinedGroupsResponse;
        setChanged();
        notifyObservers();
    }


}
