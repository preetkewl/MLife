package com.mlife.web.holder.Response;

import org.json.JSONObject;

import java.util.Observable;

/**
 * Created by milagro on 11/10/2017.
 */

public class ObjectkeyLocation extends Observable {


    JSONObject keyLocation = new JSONObject();

    public JSONObject getKeyLocation() {
        return keyLocation;
    }

    public void setKeyLocation(JSONObject keyLocation) {
        this.keyLocation = keyLocation;
        setChanged();
        notifyObservers();
    }
}
