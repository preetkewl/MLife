package com.mlife.web.holder.Response;

import com.mlife.fragments.FirstFragment;
import com.mlife.web.model.AddUserVasResponse;

import java.util.Observable;

/**
 * Created by milagro on 11/20/2017.
 */

public class ObjectAddUserVas extends Observable implements Cloneable {

    AddUserVasResponse addUserVasResponse = new AddUserVasResponse();

    public AddUserVasResponse getAddUserVasResponse() {
        return addUserVasResponse;
    }

    public void setAddUserVasResponse(AddUserVasResponse addUserVasResponse,  FirstFragment fragment) {
        this.addUserVasResponse = addUserVasResponse;
        setChanged();
        notifyObservers(fragment);
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}
