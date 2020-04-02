package com.mlife.web.holder.Response;

import com.mlife.web.model.GetPostPaymentDetailsResponse;

import java.util.Observable;

/**
 * Created by milagro on 10/10/2017.
 */

public class ObjectGetPostPaymentDetails extends Observable {

    GetPostPaymentDetailsResponse getPostPaymentDetailsResponse = new GetPostPaymentDetailsResponse();

    public GetPostPaymentDetailsResponse getGetPostPaymentDetailsResponse() {
        return getPostPaymentDetailsResponse;
    }

    public void setGetPostPaymentDetailsResponse(GetPostPaymentDetailsResponse getPostPaymentDetailsResponse) {
        this.getPostPaymentDetailsResponse = getPostPaymentDetailsResponse;
        setChanged();
        notifyObservers();
    }

}