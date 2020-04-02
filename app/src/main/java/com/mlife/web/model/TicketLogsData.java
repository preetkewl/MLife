package com.mlife.web.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by milagro on 10/10/2017.
 */

public class TicketLogsData {

    @SerializedName("ticket_log_id")
    @Expose
    private String ticketLogId;

    @SerializedName("ticket_log_ticket_id")
    @Expose
    private String ticketLogTicketId;

    @SerializedName("ticket_log_user_id")
    @Expose
    private String ticketLogUserId;

    @SerializedName("ticket_log_comment")
    @Expose
    private String ticketLogComment;

    @SerializedName("date")
    @Expose
    private String date;

    @SerializedName("ticket_log_user_type")
    @Expose
    private String ticketLogUserType;

    @SerializedName("ticket_log_posted_by")
    @Expose
    private String ticketLogPostedBy;

    public TicketLogsData() {
    }

    public TicketLogsData(String ticketLogId, String ticketLogTicketId, String ticketLogUserId, String ticketLogComment, String date, String ticketLogUserType, String ticketLogPostedBy) {
        this.ticketLogId = ticketLogId;
        this.ticketLogTicketId = ticketLogTicketId;
        this.ticketLogUserId = ticketLogUserId;
        this.ticketLogComment = ticketLogComment;
        this.date = date;
        this.ticketLogUserType = ticketLogUserType;
        this.ticketLogPostedBy = ticketLogPostedBy;
    }

    public String getTicketLogId() {
        return ticketLogId;
    }

    public void setTicketLogId(String ticketLogId) {
        this.ticketLogId = ticketLogId;
    }

    public String getTicketLogTicketId() {
        return ticketLogTicketId;
    }

    public void setTicketLogTicketId(String ticketLogTicketId) {
        this.ticketLogTicketId = ticketLogTicketId;
    }

    public String getTicketLogUserId() {
        return ticketLogUserId;
    }

    public void setTicketLogUserId(String ticketLogUserId) {
        this.ticketLogUserId = ticketLogUserId;
    }

    public String getTicketLogComment() {
        return ticketLogComment;
    }

    public void setTicketLogComment(String ticketLogComment) {
        this.ticketLogComment = ticketLogComment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTicketLogUserType() {
        return ticketLogUserType;
    }

    public void setTicketLogUserType(String ticketLogUserType) {
        this.ticketLogUserType = ticketLogUserType;
    }

    public String getTicketLogPostedBy() {
        return ticketLogPostedBy;
    }

    public void setTicketLogPostedBy(String ticketLogPostedBy) {
        this.ticketLogPostedBy = ticketLogPostedBy;
    }

}