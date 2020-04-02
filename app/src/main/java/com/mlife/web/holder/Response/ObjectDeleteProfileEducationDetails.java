package com.mlife.web.holder.Response;

import com.mlife.web.model.DeleteProfileEducationDetailsResponse;

import java.util.Observable;

/**
 * Created by milagro on 1/4/2018.
 */

public class ObjectDeleteProfileEducationDetails extends Observable {

    DeleteProfileEducationDetailsResponse deleteProfileEducationDetailsResponse = new DeleteProfileEducationDetailsResponse();

    public DeleteProfileEducationDetailsResponse getDeleteProfileEducationDetailsResponse() {
        return deleteProfileEducationDetailsResponse;
    }

    public void setDeleteProfileEducationDetailsResponse(DeleteProfileEducationDetailsResponse deleteProfileEducationDetailsResponse) {
        this.deleteProfileEducationDetailsResponse = deleteProfileEducationDetailsResponse;
        setChanged();
        notifyObservers();
    }
}
