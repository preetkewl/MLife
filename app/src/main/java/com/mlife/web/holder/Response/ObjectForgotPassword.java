package com.mlife.web.holder.Response;

import com.mlife.web.model.ForgotPasswordResponse;

import java.util.Observable;

/**
 * Created by milagro on 1/31/2018.
 */

public class ObjectForgotPassword extends Observable {

    ForgotPasswordResponse forgotPasswordResponse = new ForgotPasswordResponse();

    public ForgotPasswordResponse getForgotPasswordResponse() {
        return forgotPasswordResponse;
    }

    public void setForgotPasswordResponse(ForgotPasswordResponse forgotPasswordResponse) {
        this.forgotPasswordResponse = forgotPasswordResponse;
        setChanged();
        notifyObservers();
    }
}
