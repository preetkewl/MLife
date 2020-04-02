package com.mlife.web.holder.Response;

import com.mlife.web.model.ChangeBookingStatusResponse;

import java.util.Observable;

/**
 * Created by milagro on 10/7/2017.
 */

public class ObjectChangeBookingStatus extends Observable {
    ChangeBookingStatusResponse changeBookingStatusResponse = new ChangeBookingStatusResponse();

    public ChangeBookingStatusResponse getChangeBookingStatusResponse() {
        return changeBookingStatusResponse;
    }

    public void setChangeBookingStatusResponse(ChangeBookingStatusResponse changeBookingStatusResponse) {
        this.changeBookingStatusResponse = changeBookingStatusResponse;
        setChanged();
        notifyObservers();
    }
}
