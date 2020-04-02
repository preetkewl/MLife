package com.mlife.web.holder.Response;

import com.mlife.web.model.ProjectGalleryResponse;

import java.util.Observable;

/**
 * Created by milagro on 10/10/2017.
 */

public class ObjectProjectGallery extends Observable{

    ProjectGalleryResponse projectGalleryResponse = new ProjectGalleryResponse();

    public ProjectGalleryResponse getProjectGalleryResponse() {
        return projectGalleryResponse;
    }

    public void setProjectGalleryResponse(ProjectGalleryResponse projectGalleryResponse) {
        this.projectGalleryResponse = projectGalleryResponse;
        setChanged();
        notifyObservers();
    }
}
