package com.mlife.web.holder.Response;

import com.mlife.web.model.JoinGroupResponse;

import java.util.Observable;

/**
 * Created by milagro on 10/3/2017.
 */

public class ObjectJoinGroup extends Observable {

    JoinGroupResponse joinGroupResponse = new JoinGroupResponse();

    public JoinGroupResponse getJoinGroupResponse() {
        return joinGroupResponse;
    }

    public void setJoinGroupResponse(JoinGroupResponse joinGroupResponse) {
        this.joinGroupResponse = joinGroupResponse;
        setChanged();
        notifyObservers();
    }

}
