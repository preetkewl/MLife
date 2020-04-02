package com.mlife.web.holder.Response;

import com.mlife.web.model.SendNewEnquiryResponse;

import java.util.Observable;

/**
 * Created by milagro on 12/28/2017.
 */

public class ObjectSendNewEnquiry extends Observable{

    SendNewEnquiryResponse sendNewEnquiryResponse = new SendNewEnquiryResponse();

    public SendNewEnquiryResponse getSendNewEnquiryResponse() {
        return sendNewEnquiryResponse;
    }

    public void setSendNewEnquiryResponse(SendNewEnquiryResponse sendNewEnquiryResponse) {
        this.sendNewEnquiryResponse = sendNewEnquiryResponse;
        setChanged();
        notifyObservers();
    }
}
