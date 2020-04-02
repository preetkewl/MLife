package com.mlife.web.holder.Response;

import com.mlife.web.model.GetTimeSlotResponse;

import java.util.Observable;

/**
 * Created by milagro on 11/23/2017.
 */

public class ObjectGetTimeSlot extends Observable{

    GetTimeSlotResponse getTimeSlotResponse = new GetTimeSlotResponse();

    public GetTimeSlotResponse getGetTimeSlotResponse() {
        return getTimeSlotResponse;
    }

    public void setGetTimeSlotResponse(GetTimeSlotResponse getTimeSlotResponse) {
        this.getTimeSlotResponse = getTimeSlotResponse;
        setChanged();
        notifyObservers();
    }
}
