package com.mlife.adapter;

/**
 * Created by milagro on 9/4/2017.
 */

public class EventsGetterSetter {

    int id;
    String Name,Detail;
    Boolean Response;

    public EventsGetterSetter(int id, String name, String detail, Boolean response) {
        this.id = id;
        Name = name;
        Detail = detail;
        Response = response;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDetail() {
        return Detail;
    }

    public void setDetail(String detail) {
        Detail = detail;
    }

    public Boolean getResponse() {
        return Response;
    }

    public void setResponse(Boolean response) {
        Response = response;
    }
}
