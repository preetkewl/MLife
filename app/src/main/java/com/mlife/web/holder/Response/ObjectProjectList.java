package com.mlife.web.holder.Response;

import com.mlife.web.model.ProjectListResponse;

import java.util.Observable;

/**
 * Created by milagro on 10/10/2017.
 */

public class ObjectProjectList extends Observable {

    ProjectListResponse projectListResponse = new ProjectListResponse();

    public ProjectListResponse getProjectListResponse() {
        return projectListResponse;
    }

    public void setProjectListResponse(ProjectListResponse projectListResponse) {
        this.projectListResponse = projectListResponse;
        setChanged();
        notifyObservers();
    }
}
