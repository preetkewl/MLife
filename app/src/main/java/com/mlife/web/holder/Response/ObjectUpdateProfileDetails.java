package com.mlife.web.holder.Response;

import com.mlife.web.model.UpdateProfileDetailsResponse;

import java.util.Observable;

/**
 * Created by milagro on 12/26/2017.
 */

public class ObjectUpdateProfileDetails extends Observable {
    UpdateProfileDetailsResponse updateProfileDetailsResponse = new UpdateProfileDetailsResponse();


    public UpdateProfileDetailsResponse getUpdateProfileDetailsResponse() {
        return updateProfileDetailsResponse;
    }

    public void setUpdateProfileDetailsResponse(UpdateProfileDetailsResponse updateProfileDetailsResponse) {
        this.updateProfileDetailsResponse = updateProfileDetailsResponse;
        setChanged();
        notifyObservers();
    }
}
