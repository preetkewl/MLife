package com.mlife.web.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by milagro on 10/9/2017.
 */

public class TicketListData {

    @SerializedName("ticket_id")
    @Expose
    private String ticketId;

    @SerializedName("ticket_subject")
    @Expose
    private String ticketSubject;

    @SerializedName("ticket_priorty")
    @Expose
    private String ticketPriorty;

    @SerializedName("ticket_dec")
    @Expose
    private String ticketDec;

    @SerializedName("ticket_ref")
    @Expose
    private String ticketRef;

    @SerializedName("ticket_status")
    @Expose
    private String ticketStatus;

    @SerializedName("ticket_type")
    @Expose
    private String ticketType;

    @SerializedName("date")
    @Expose
    private String date;

    @SerializedName("ticket_posted_by")
    @Expose
    private String ticketPostedBy;

    @SerializedName("ticket_attachment_name")
    @Expose
    private List<Object> ticketAttachmentName = null;

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getTicketSubject() {
        return ticketSubject;
    }

    public void setTicketSubject(String ticketSubject) {
        this.ticketSubject = ticketSubject;
    }

    public String getTicketPriorty() {
        return ticketPriorty;
    }

    public void setTicketPriorty(String ticketPriorty) {
        this.ticketPriorty = ticketPriorty;
    }

    public String getTicketDec() {
        return ticketDec;
    }

    public void setTicketDec(String ticketDec) {
        this.ticketDec = ticketDec;
    }

    public String getTicketRef() {
        return ticketRef;
    }

    public void setTicketRef(String ticketRef) {
        this.ticketRef = ticketRef;
    }

    public String getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(String ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTicketPostedBy() {
        return ticketPostedBy;
    }

    public void setTicketPostedBy(String ticketPostedBy) {
        this.ticketPostedBy = ticketPostedBy;
    }

    public List<Object> getTicketAttachmentName() {
        return ticketAttachmentName;
    }

    public void setTicketAttachmentName(List<Object> ticketAttachmentName) {
        this.ticketAttachmentName = ticketAttachmentName;
    }

}