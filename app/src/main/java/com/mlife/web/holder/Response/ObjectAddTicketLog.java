package com.mlife.web.holder.Response;

import com.mlife.web.model.AddTicketLogResponse;

import java.util.Observable;

/**
 * Created by milagro on 10/10/2017.
 */

public class ObjectAddTicketLog extends Observable {

    AddTicketLogResponse addTicketLogResponse = new AddTicketLogResponse();

    public AddTicketLogResponse getAddTicketLogResponse() {
        return addTicketLogResponse;
    }

    public void setAddTicketLogResponse(AddTicketLogResponse addTicketLogResponse) {
        this.addTicketLogResponse = addTicketLogResponse;
        setChanged();
        notifyObservers();
    }

}