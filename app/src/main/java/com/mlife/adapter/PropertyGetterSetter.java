package com.mlife.adapter;

/**
 * Created by milagro on 11/21/2017.
 */

public class PropertyGetterSetter {

    String ProjectName, ProjectId;

    public PropertyGetterSetter(String projectName, String projectId) {
        ProjectName = projectName;
        ProjectId = projectId;
    }

    public String getProjectName() {
        return ProjectName;
    }

    public void setProjectName(String projectName) {
        ProjectName = projectName;
    }

    public String getProjectId() {
        return ProjectId;
    }

    public void setProjectId(String projectId) {
        ProjectId = projectId;
    }
}
