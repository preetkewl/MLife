package com.mlife.web.holder.Response;

import com.mlife.web.model.RemoveGroupMemberResponse;

import java.util.Observable;

/**
 * Created by milagro on 1/17/2018.
 */

public class ObjectRemoveGroupMember extends Observable {

    RemoveGroupMemberResponse removeGroupMemberResponse = new RemoveGroupMemberResponse();

    public RemoveGroupMemberResponse getRemoveGroupMemberResponse() {
        return removeGroupMemberResponse;
    }

    public void setRemoveGroupMemberResponse(RemoveGroupMemberResponse removeGroupMemberResponse) {
        this.removeGroupMemberResponse = removeGroupMemberResponse;
        setChanged();
        notifyObservers();
    }
}