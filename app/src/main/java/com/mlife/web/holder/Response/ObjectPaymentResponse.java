package com.mlife.web.holder.Response;

import com.mlife.web.model.MPaymentResponse;

import java.util.Observable;

/**
 * Created by Neha Thakur on 10/9/2017.
 */

public class ObjectPaymentResponse extends Observable {

    MPaymentResponse mPaymentResponse = new MPaymentResponse();

    public MPaymentResponse getmPaymentResponse() {
        return mPaymentResponse;
    }

    public void setmPaymentResponse(MPaymentResponse mPaymentResponse) {
        this.mPaymentResponse = mPaymentResponse;
        setChanged();
        notifyObservers();
    }
}
