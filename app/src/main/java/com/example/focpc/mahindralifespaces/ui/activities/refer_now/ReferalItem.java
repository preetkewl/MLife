package com.example.focpc.mahindralifespaces.ui.activities.refer_now;

import java.util.List;

/**
 * Created by foc pc on 15-12-2017.
 */

public class ReferalItem {

    private int referral_id;
    private String referral_name;
    private String referral_image;
    private String referral_end_date;
    private boolean isSelected;
    private List<ReferralExUserItem> referral_ex_users;

    public int getReferralId() {
        return referral_id;
    }

    public void setReferralId(int id) {
        this.referral_id = id;
    }

    public String getReferral_name() {
        return referral_name;
    }

    public void setReferral_name(String referral_name) {
        this.referral_name = referral_name;
    }

    public String getReferral_image() {
        return referral_image;
    }

    public void setReferral_image(String referral_image) {
        this.referral_image = referral_image;
    }

    public String getReferral_end_date() {
        return referral_end_date;
    }

    public void setReferral_end_date(String referral_end_date) {
        this.referral_end_date = referral_end_date;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }


    public List<ReferralExUserItem> getReferral_ex_users() {
        return referral_ex_users;
    }

    public void setReferral_ex_users(List<ReferralExUserItem> referral_ex_users) {
        this.referral_ex_users = referral_ex_users;
    }
}
