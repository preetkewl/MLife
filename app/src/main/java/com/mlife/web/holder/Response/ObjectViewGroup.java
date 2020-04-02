package com.mlife.web.holder.Response;

import com.mlife.web.model.ViewGroupResponse;

import java.util.Observable;

/**
 * Created by milagro on 10/4/2017.
 */

public class ObjectViewGroup extends Observable {

    ViewGroupResponse viewGroupResponse = new ViewGroupResponse();

    public ViewGroupResponse getViewGroupResponse() {
        return viewGroupResponse;
    }

    public void setViewGroupResponse(ViewGroupResponse viewGroupResponse) {
        this.viewGroupResponse = viewGroupResponse;
        setChanged();
        notifyObservers();
    }
}
