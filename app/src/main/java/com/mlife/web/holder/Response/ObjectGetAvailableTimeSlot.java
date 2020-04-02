package com.mlife.web.holder.Response;

import com.mlife.web.model.GetAvailableTimeSlotResponse;

import java.util.Observable;

/**
 * Created by milagro on 11/27/2017.
 */

public class ObjectGetAvailableTimeSlot extends Observable{

    GetAvailableTimeSlotResponse getAvailableTimeSlotResponse = new GetAvailableTimeSlotResponse();

    public GetAvailableTimeSlotResponse getGetAvailableTimeSlotResponse() {
        return getAvailableTimeSlotResponse;
    }

    public void setGetAvailableTimeSlotResponse(GetAvailableTimeSlotResponse getAvailableTimeSlotResponse) {
        this.getAvailableTimeSlotResponse = getAvailableTimeSlotResponse;
        setChanged();
        notifyObservers();
    }

}