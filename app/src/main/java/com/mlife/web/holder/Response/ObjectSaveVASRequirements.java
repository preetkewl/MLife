package com.mlife.web.holder.Response;

import com.mlife.web.model.SaveVASRequirementsResponse;
import java.util.Observable;

/**
 * Created by milagro on 1/11/2018.
 */

public class ObjectSaveVASRequirements extends Observable {

    SaveVASRequirementsResponse saveVASRequirementsResponse = new SaveVASRequirementsResponse();

    public SaveVASRequirementsResponse getSaveVASRequirementsResponse() {
        return saveVASRequirementsResponse;
    }

    public void setSaveVASRequirementsResponse(SaveVASRequirementsResponse saveVASRequirementsResponse) {
        this.saveVASRequirementsResponse = saveVASRequirementsResponse;
        setChanged();
        notifyObservers();
    }

}