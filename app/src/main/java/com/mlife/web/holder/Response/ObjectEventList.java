package com.mlife.web.holder.Response;

import com.mlife.web.model.EventListResponse;

import java.util.Observable;

/**
 * Created by milagro on 10/5/2017.
 */

public class ObjectEventList extends Observable {

    EventListResponse eventListResponse = new EventListResponse();

    public EventListResponse getEventListResponse() {
        return eventListResponse;
    }

    public void setEventListResponse(EventListResponse eventListResponse) {
        this.eventListResponse = eventListResponse;
        setChanged();
        notifyObservers();
    }
}
