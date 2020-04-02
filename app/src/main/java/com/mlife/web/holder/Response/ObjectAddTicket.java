package com.mlife.web.holder.Response;

import com.mlife.web.model.AddTicketResponse;

import java.util.Observable;

/**
 * Created by milagro on 10/9/2017.
 */

public class ObjectAddTicket extends Observable {

    AddTicketResponse addTicketResponse = new AddTicketResponse();

    public AddTicketResponse getAddTicketResponse() {
        return addTicketResponse;
    }

    public void setAddTicketResponse(AddTicketResponse addTicketResponse) {
        this.addTicketResponse = addTicketResponse;
        setChanged();
        notifyObservers();
    }
}
