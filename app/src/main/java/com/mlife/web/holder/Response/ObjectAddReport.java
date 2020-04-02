package com.mlife.web.holder.Response;

import com.mlife.web.model.AddReportResponse;

import java.util.Observable;

/**
 * Created by milagro on 10/16/2017.
 */

public class ObjectAddReport extends Observable {

    AddReportResponse addReportResponse = new AddReportResponse();

    public AddReportResponse getAddReportResponse() {
        return addReportResponse;
    }

    public void setAddReportResponse(AddReportResponse addReportResponse) {
        this.addReportResponse = addReportResponse;
        setChanged();
        notifyObservers();
    }
}
