package com.mlife.web.holder.Response;

import com.mlife.fragments.FirstFragment;
import com.mlife.web.model.AddedValueListResponse;

import java.util.Observable;

/**
 * Created by milagro on 10/7/2017.
 */

public class ObjectAddedValueList extends Observable implements Cloneable {

    AddedValueListResponse addedValueListResponse = new AddedValueListResponse();

    public AddedValueListResponse getAddedValueListData() {
        return addedValueListResponse;
    }

    public void setAddedValueListData(AddedValueListResponse addedValueListResponse, FirstFragment fragment) {
        this.addedValueListResponse = addedValueListResponse;
        setChanged();
        notifyObservers(fragment);
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}