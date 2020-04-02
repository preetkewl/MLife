package com.mlife.web.holder.Response;

import com.mlife.web.model.LoadProfileSettingsResponse;

import java.util.Observable;

/**
 * Created by milagro on 1/15/2018.
 */

public class ObjectLoadProfileSettings extends Observable{

    LoadProfileSettingsResponse loadProfileSettingsResponse = new LoadProfileSettingsResponse();

    public LoadProfileSettingsResponse getLoadProfileSettingsResponse() {
        return loadProfileSettingsResponse;
    }

    public void setLoadProfileSettingsResponse(LoadProfileSettingsResponse loadProfileSettingsResponse) {
        this.loadProfileSettingsResponse = loadProfileSettingsResponse;
        setChanged();
        notifyObservers();
    }
}
