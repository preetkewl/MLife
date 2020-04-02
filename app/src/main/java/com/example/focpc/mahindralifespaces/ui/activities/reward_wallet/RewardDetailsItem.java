package com.example.focpc.mahindralifespaces.ui.activities.reward_wallet;

import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by foc pc on 06-12-2017.
 */

public class RewardDetailsItem implements Serializable, Comparable<RewardDetailsItem>{

    private String claim_direct;
    private Integer claim_status;
    private String vendor_logo;
    private String vendor_name;
    private String claim_date;
    private String eligible_date;
    private Integer type;
    private Integer program_status;
    private Integer visit_left;
    private String prog_end_date;
    private String user_prog_id;
    private String vendor_id;
    private String prog_name;
    private String expiry_date;


    public String getClaim_direct() {
        return claim_direct;
    }

    public void setClaim_direct(String claim_direct) {
        this.claim_direct = claim_direct;
    }

    public Integer getClaim_status() {
        return claim_status;
    }

    public void setClaim_status(Integer claim_status) {
        this.claim_status = claim_status;
    }

    public String getVendor_logo() {
        return vendor_logo;
    }

    public void setVendor_logo(String vendor_logo) {
        this.vendor_logo = vendor_logo;
    }

    public String getVendor_name() {
        return vendor_name;
    }

    public void setVendor_name(String vendor_name) {
        this.vendor_name = vendor_name;
    }

    public String getClaim_date() {
        return claim_date;
    }

    public void setClaim_date(String claim_date) {
        this.claim_date = claim_date;
    }

    public String getEligible_date() {
        return eligible_date;
    }

    public void setEligible_date(String eligible_date) {
        this.eligible_date = eligible_date;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getProgram_status() {
        return program_status;
    }

    public void setProgram_status(Integer program_status) {
        this.program_status = program_status;
    }

    public Integer getVisit_left() {
        return visit_left;
    }

    public void setVisit_left(Integer visit_left) {
        this.visit_left = visit_left;
    }

    public String getProg_end_date() {
        return prog_end_date;
    }

    public void setProg_end_date(String prog_end_date) {
        this.prog_end_date = prog_end_date;
    }

    public String getUser_prog_id() {
        return user_prog_id;
    }

    public void setUser_prog_id(String user_prog_id) {
        this.user_prog_id = user_prog_id;
    }

    public String getVendor_id() {
        return vendor_id;
    }

    public void setVendor_id(String vendor_id) {
        this.vendor_id = vendor_id;
    }

    public String getProg_name() {
        return prog_name;
    }

    public void setProg_name(String prog_name) {
        this.prog_name = prog_name;
    }

    public String getExpiry_date() {
        return expiry_date;
    }

    public void setExpiry_date(String expiry_date) {
        this.expiry_date = expiry_date;
    }

    public Date getDateFromtimeStamp(){
        String reqDate;
        if (claim_status==3){
            reqDate = expiry_date;
        } else {
            reqDate = claim_date;

        }
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return  sdf.parse(reqDate);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public int compareTo(@NonNull RewardDetailsItem rewardDetailsItem) {
        return  -(getDateFromtimeStamp().compareTo(rewardDetailsItem.getDateFromtimeStamp()));
    }
}
