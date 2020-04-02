package com.mlife.web.holder.Response;

import com.mlife.web.model.LoginResponse;
import java.util.Observable;

/**
 * Created by milagro on 9/18/2017.
 */

public class ObjectLoginResponse extends Observable {

    LoginResponse loginResponse = new LoginResponse();

    public LoginResponse getLoginResponse() {
        return loginResponse;
    }

    public void setLoginResponse(LoginResponse loginResponse) {
        this.loginResponse = loginResponse;
        setChanged();
        notifyObservers();
    }
}
