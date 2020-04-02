package com.mlife.web.holder.Response;

import com.mlife.web.model.SetProfileImageResponse;

import java.util.Observable;

/**
 * Created by milagro on 12/22/2017.
 */

public class ObjectSetProfileImage extends Observable {

    SetProfileImageResponse setProfileImageResponse = new SetProfileImageResponse();

    public SetProfileImageResponse getSetProfileImageResponse() {
        return setProfileImageResponse;
    }

    public void setSetProfileImageResponse(SetProfileImageResponse setProfileImageResponse) {
        this.setProfileImageResponse = setProfileImageResponse;
        setChanged();
        notifyObservers();

    }
}
