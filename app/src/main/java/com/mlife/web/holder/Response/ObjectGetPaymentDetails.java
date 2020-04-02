package com.mlife.web.holder.Response;

import com.mlife.web.model.GetPaymentDetailsResponse;

import java.util.Observable;

/**
 * Created by milagro on 10/7/2017.
 */

public class ObjectGetPaymentDetails  extends Observable {

    GetPaymentDetailsResponse getPaymentDetailsResponse = new GetPaymentDetailsResponse();

    public GetPaymentDetailsResponse getGetPaymentDetailsResponse() {
        return getPaymentDetailsResponse;
    }

    public void setGetPaymentDetailsResponse(GetPaymentDetailsResponse getPaymentDetailsResponse) {
        this.getPaymentDetailsResponse = getPaymentDetailsResponse;
        setChanged();
        notifyObservers();
    }
}
