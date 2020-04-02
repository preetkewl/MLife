package com.mlife.web.holder.Response;

import com.mlife.web.model.UpdateGroupJoinRequestResponse;

import java.util.Observable;

/**
 * Created by milagro on 1/17/2018.
 */

public class ObjectUpdateGroupJoinRequest extends Observable{

    UpdateGroupJoinRequestResponse updateGroupJoinRequestResponse = new UpdateGroupJoinRequestResponse();

    public UpdateGroupJoinRequestResponse getUpdateGroupJoinRequestResponse() {
        return updateGroupJoinRequestResponse;
    }

    public void setUpdateGroupJoinRequestResponse(UpdateGroupJoinRequestResponse updateGroupJoinRequestResponse) {
        this.updateGroupJoinRequestResponse = updateGroupJoinRequestResponse;
        setChanged();
        notifyObservers();
    }
}
