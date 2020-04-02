package com.mlife.web.holder.Response;

import com.mlife.web.model.ForgotPasswordVerifyResponse;

import java.util.Observable;

/**
 * Created by milagro on 3/26/2018.
 */

public class ObjectForgotPasswordVerify extends Observable {

    ForgotPasswordVerifyResponse  forgotPasswordVerifyResponse = new ForgotPasswordVerifyResponse();

    public ForgotPasswordVerifyResponse getForgotPasswordVerifyResponse() {
        return forgotPasswordVerifyResponse;
    }

    public void setForgotPasswordVerifyResponse(ForgotPasswordVerifyResponse forgotPasswordVerifyResponse) {
        this.forgotPasswordVerifyResponse = forgotPasswordVerifyResponse;
        setChanged();
        notifyObservers();
    }
}
