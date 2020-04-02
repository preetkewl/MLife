package com.mlife.web.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by milagro on 10/16/2017.
 */

public class NotificationListData {

    @SerializedName("notification_id")
    @Expose
    private String notificationId;
    @SerializedName("notification_type")
    @Expose
    private String notificationType;
    @SerializedName("notification_comment")
    @Expose
    private String notificationComment;
    @SerializedName("date")
    @Expose
    private String date;

    public String getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(String notificationId) {
        this.notificationId = notificationId;
    }

    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }

    public String getNotificationComment() {
        return notificationComment;
    }

    public void setNotificationComment(String notificationComment) {
        this.notificationComment = notificationComment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}