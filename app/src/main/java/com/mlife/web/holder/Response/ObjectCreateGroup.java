package com.mlife.web.holder.Response;

import com.mlife.web.model.CreateGroupResponse;

import java.util.Observable;

/**
 * Created by milagro on 9/30/2017.
 */

public class ObjectCreateGroup extends Observable {
    CreateGroupResponse createGroupResponse = new CreateGroupResponse();

    public CreateGroupResponse getCreateGroupResponse() {
        return createGroupResponse;
    }

    public void setCreateGroupResponse(CreateGroupResponse createGroupResponse) {
        this.createGroupResponse = createGroupResponse;
        setChanged();
        notifyObservers();
    }
}
