package com.mlife.web.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by milagro on 10/7/2017.
 */

public class GetPaymentDetailsDetails implements Serializable {

    @SerializedName("paid")
    @Expose
    private Integer paid;

    @SerializedName("dType")
    @Expose
    private String dType;
    @SerializedName("documentId")
    @Expose
    private String documentId;
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("sDate")
    @Expose
    private String sDate;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("ref")
    @Expose
    private String ref;

    @SerializedName("paymentId")
    @Expose
    private String paymentId ;

    @SerializedName("description")
    @Expose
    private String description;

    public String getsDate() {return sDate;}

    public void setsDate(String sDate) {this.sDate = sDate;}

    public Integer getPaid() {
        return paid;
    }

    public void setPaid(Integer paid) {
        this.paid = paid;
    }

    public String getDType() {
        return dType;
    }

    public void setDType(String dType) {
        this.dType = dType;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getdType() {
        return dType;
    }

    public void setdType(String dType) {
        this.dType = dType;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }
}