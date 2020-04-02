package com.mlife.adapter;

/**
 * Created by milagro on 9/4/2017.
 */

public class ExploreGroupGetterSetter {

    String id, GroupName, Members;


    public ExploreGroupGetterSetter(String id, String groupName, String members) {
        this.id = id;
        GroupName = groupName;
        Members = members;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroupName() {
        return GroupName;
    }

    public void setGroupName(String groupName) {
        GroupName = groupName;
    }

    public String getMembers() {
        return Members;
    }

    public void setMembers(String members) {
        Members = members;
    }


}
