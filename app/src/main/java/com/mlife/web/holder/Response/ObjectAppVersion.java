package com.mlife.web.holder.Response;

import com.mlife.web.model.AppVersion;

import java.util.Observable;

/**
 * Created by milagro on 3/8/2018.
 */

public class ObjectAppVersion extends Observable {

    AppVersion appVersion = new AppVersion();

    public AppVersion getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(AppVersion appVersion) {
        this.appVersion = appVersion;
        setChanged();
        notifyObservers();
    }
}
