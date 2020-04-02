package com.mlife.web.holder.Response;

import com.mlife.web.model.TicketLogsResponse;

import java.util.Observable;

/**
 * Created by milagro on 10/10/2017.
 */

public class ObjectTicketLogs extends Observable{

    TicketLogsResponse ticketLogsResponse = new TicketLogsResponse();


    public TicketLogsResponse getTicketLogsResponse() {
        return ticketLogsResponse;
    }

    public void setTicketLogsResponse(TicketLogsResponse ticketLogsResponse) {
        this.ticketLogsResponse = ticketLogsResponse;
        setChanged();
        notifyObservers();
    }
}
