package com.mlife.web.holder.Response;

import com.mlife.web.model.Example;

import java.util.Observable;

public class ObjectLoginOTPResponse extends Observable {

    Example loginResponse = new Example();

    public Example getLoginResponse() {
        return loginResponse;
    }

    public void setLoginResponse(Example loginResponse) {
        this.loginResponse = loginResponse;
        setChanged();
        notifyObservers();
    }
}
