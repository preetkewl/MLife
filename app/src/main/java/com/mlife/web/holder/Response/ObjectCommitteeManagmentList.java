package com.mlife.web.holder.Response;

import com.mlife.web.model.CommitteeManagmentListResponse;

import java.util.Observable;

/**
 * Created by milagro on 10/14/2017.
 */

public class ObjectCommitteeManagmentList extends Observable {

    CommitteeManagmentListResponse committeeManagmentListResponse = new CommitteeManagmentListResponse();

    public CommitteeManagmentListResponse getCommitteeManagmentListResponse() {
        return committeeManagmentListResponse;
    }

    public void setCommitteeManagmentListResponse(CommitteeManagmentListResponse committeeManagmentListResponse) {
        this.committeeManagmentListResponse = committeeManagmentListResponse;
        setChanged();
        notifyObservers();
    }

}

