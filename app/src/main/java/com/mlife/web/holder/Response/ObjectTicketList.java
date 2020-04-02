package com.mlife.web.holder.Response;

import com.mlife.web.model.TicketListResponse;

import java.util.Observable;

/**
 * Created by milagro on 10/9/2017.
 */

public class ObjectTicketList  extends Observable {

    TicketListResponse ticketListResponse = new TicketListResponse();

    public TicketListResponse getTicketListResponse() {
        return ticketListResponse;
    }

    public void setTicketListResponse(TicketListResponse ticketListResponse) {
        this.ticketListResponse = ticketListResponse;
        setChanged();
        notifyObservers();
    }
}
