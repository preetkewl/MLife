package com.mlife.web.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by milagro on 11/6/2017.
 */

public class GetDownloadDocumentsData  {

    @SerializedName("dType")
    @Expose
    private String dType;

    @SerializedName("documentId")
    @Expose
    private String documentId;

    @SerializedName("name")
    @Expose
    private String name;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}