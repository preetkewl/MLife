package com.mlife.web.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by milagro on 10/9/2017.
 */

public class OfferListData {

    @SerializedName("offer_id")
    @Expose
    private String offerId;
    @SerializedName("offer_title")
    @Expose
    private String offerTitle;
    @SerializedName("offer_price")
    @Expose
    private String offerPrice;
    @SerializedName("offer_dec")
    @Expose
    private String offerDec;
    @SerializedName("offer_valid_date")
    @Expose
    private String offerValidDate;
    @SerializedName("offer_type")
    @Expose
    private String offerType;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("offer_posted_by")
    @Expose
    private String offerPostedBy;
    @SerializedName("offer_contact")
    @Expose
    private String offerContact;
    @SerializedName("offer_attachment_name")
    @Expose
    private List<Object> offerAttachmentName = null;

    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public String getOfferTitle() {
        return offerTitle;
    }

    public void setOfferTitle(String offerTitle) {
        this.offerTitle = offerTitle;
    }

    public String getOfferPrice() {
        return offerPrice;
    }

    public void setOfferPrice(String offerPrice) {
        this.offerPrice = offerPrice;
    }

    public String getOfferDec() {
        return offerDec;
    }

    public void setOfferDec(String offerDec) {
        this.offerDec = offerDec;
    }

    public String getOfferValidDate() {
        return offerValidDate;
    }

    public void setOfferValidDate(String offerValidDate) {
        this.offerValidDate = offerValidDate;
    }

    public String getOfferType() {
        return offerType;
    }

    public void setOfferType(String offerType) {
        this.offerType = offerType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOfferPostedBy() {
        return offerPostedBy;
    }

    public void setOfferPostedBy(String offerPostedBy) {
        this.offerPostedBy = offerPostedBy;
    }

    public String getOfferContact() {
        return offerContact;
    }

    public void setOfferContact(String offerContact) {
        this.offerContact = offerContact;
    }

    public List<Object> getOfferAttachmentName() {
        return offerAttachmentName;
    }

    public void setOfferAttachmentName(List<Object> offerAttachmentName) {
        this.offerAttachmentName = offerAttachmentName;
    }

}