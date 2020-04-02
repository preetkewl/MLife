package com.mlife.web.holder.Response;

import com.mlife.web.model.MembersbyGroupResponse;

import java.util.Observable;

/**
 * Created by milagro on 10/12/2017.
 */

public class ObjectMembersbyGroup extends Observable {

    MembersbyGroupResponse membersbyGroupResponse = new MembersbyGroupResponse();

    public MembersbyGroupResponse getMembersbyGroupResponse() {
        return membersbyGroupResponse;
    }

    public void setMembersbyGroupResponse(MembersbyGroupResponse membersbyGroupResponse) {
        this.membersbyGroupResponse = membersbyGroupResponse;
        setChanged();
        notifyObservers();
    }
}
