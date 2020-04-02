package com.mlife.web.holder.Response;

import com.mlife.web.model.ProjectAmenitiesResponse;

import java.util.Observable;

/**
 * Created by milagro on 10/10/2017.
 */

public class ObjectProjectAmenities extends Observable {

    ProjectAmenitiesResponse projectAmenitiesResponse = new ProjectAmenitiesResponse();

    public ProjectAmenitiesResponse getProjectAmenitiesResponse() {
        return projectAmenitiesResponse;
    }

    public void setProjectAmenitiesResponse(ProjectAmenitiesResponse projectAmenitiesResponse) {
        this.projectAmenitiesResponse = projectAmenitiesResponse;
        setChanged();
        notifyObservers();
    }
}
