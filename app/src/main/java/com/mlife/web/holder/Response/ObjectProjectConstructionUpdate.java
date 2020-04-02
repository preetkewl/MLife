package com.mlife.web.holder.Response;

import com.mlife.web.model.ProjectConstructionUpdateResponse;

import java.util.Observable;

/**
 * Created by milagro on 10/10/2017.
 */

public class ObjectProjectConstructionUpdate extends Observable{

    ProjectConstructionUpdateResponse projectConstructionUpdateResponse = new ProjectConstructionUpdateResponse();

    public ProjectConstructionUpdateResponse getProjectConstructionUpdateResponse() {
        return projectConstructionUpdateResponse;
    }

    public void setProjectConstructionUpdateResponse(ProjectConstructionUpdateResponse projectConstructionUpdateResponse) {
        this.projectConstructionUpdateResponse = projectConstructionUpdateResponse;
        setChanged();
        notifyObservers();
    }
}