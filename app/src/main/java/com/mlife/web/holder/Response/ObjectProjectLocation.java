package com.mlife.web.holder.Response;

import com.mlife.web.model.ProjectLocationResponse;

import java.util.Observable;

/**
 * Created by milagro on 10/10/2017.
 */

public class ObjectProjectLocation extends Observable {

    ProjectLocationResponse projectLocationResponse = new ProjectLocationResponse();

    public ProjectLocationResponse getProjectLocationResponse() {
        return projectLocationResponse;
    }

    public void setProjectLocationResponse(ProjectLocationResponse projectLocationResponse) {
        this.projectLocationResponse = projectLocationResponse;
        setChanged();
        notifyObservers();
    }
}
