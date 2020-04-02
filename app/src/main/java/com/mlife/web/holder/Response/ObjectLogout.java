package com.mlife.web.holder.Response;

import com.mlife.web.model.LogoutResponse;

import java.util.Observable;

/**
 * Created by milagro on 2/15/2018.
 */

public class ObjectLogout extends Observable {

    LogoutResponse logoutResponse = new LogoutResponse();

    public LogoutResponse getLogoutResponse() {
        return logoutResponse;
    }

    public void setLogoutResponse(LogoutResponse logoutResponse) {
        this.logoutResponse = logoutResponse;
        setChanged();
        notifyObservers();

    }
}
