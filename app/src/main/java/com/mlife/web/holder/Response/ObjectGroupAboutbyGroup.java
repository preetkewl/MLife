package com.mlife.web.holder.Response;

import com.mlife.web.model.GroupAboutbyGroupResponse;

import java.util.Observable;

/**
 * Created by milagro on 10/12/2017.
 */

public class ObjectGroupAboutbyGroup extends Observable {

    GroupAboutbyGroupResponse groupAboutbyGroupResponse = new GroupAboutbyGroupResponse();

    public GroupAboutbyGroupResponse getGroupAboutbyGroupResponse() {
        return groupAboutbyGroupResponse;
    }

    public void setGroupAboutbyGroupResponse(GroupAboutbyGroupResponse groupAboutbyGroupResponse) {
        this.groupAboutbyGroupResponse = groupAboutbyGroupResponse;
        setChanged();
        notifyObservers();
    }
}
