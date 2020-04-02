package com.mlife.web.holder.Response;

import com.mlife.web.model.LoadVASSettingsResponse;

import java.util.Observable;

/**
 * Created by milagro on 1/10/2018.
 */

public class ObjectLoadVASSettings extends Observable {

    LoadVASSettingsResponse loadVASSettingsResponse = new LoadVASSettingsResponse();

    public LoadVASSettingsResponse getLoadVASSettingsResponse() {
        return loadVASSettingsResponse;
    }

    public void setLoadVASSettingsResponse(LoadVASSettingsResponse loadVASSettingsResponse) {
        this.loadVASSettingsResponse = loadVASSettingsResponse;
        setChanged();
        notifyObservers();
    }

}