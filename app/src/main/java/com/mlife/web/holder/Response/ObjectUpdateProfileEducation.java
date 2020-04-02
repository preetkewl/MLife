package com.mlife.web.holder.Response;

import com.mlife.web.model.UpdateProfileEducationResponse;

import java.util.Observable;

/**
 * Created by milagro on 12/26/2017.
 */

public class ObjectUpdateProfileEducation extends Observable{

    UpdateProfileEducationResponse updateProfileEducationResponse = new UpdateProfileEducationResponse();

    public UpdateProfileEducationResponse getUpdateProfileEducationResponse() {
        return updateProfileEducationResponse;
    }

    public void setUpdateProfileEducationResponse(UpdateProfileEducationResponse updateProfileEducationResponse) {
        this.updateProfileEducationResponse = updateProfileEducationResponse;
        setChanged();
        notifyObservers();
    }
}