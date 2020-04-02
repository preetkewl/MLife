package com.mlife.web.holder.Response;

import com.mlife.web.model.NotificationListResponse;

import java.util.Observable;

/*
 * Created by milagro on 10/16/2017.
 */

public class ObjectNotificationList extends Observable {

    NotificationListResponse notificationListResponse = new NotificationListResponse();

    public NotificationListResponse getNotificationListResponse() {
        return notificationListResponse;
    }

    public void setNotificationListResponse(NotificationListResponse notificationListResponse) {
        this.notificationListResponse = notificationListResponse;
        setChanged();
        notifyObservers();
    }

}