package com.example.focpc.mahindralifespaces.ui.activities.select_contact;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by user on 13-06-2017.
 */

public class ReferUserListItem {

    private List<ReferalContactItem> multipleReferrals = null;

    public ReferUserListItem(List<ReferalContactItem> multipleReferrals) {
        this.multipleReferrals = multipleReferrals;
    }

    public List<ReferalContactItem> getMultipleReferrals() {
        return multipleReferrals;
    }

    public void setMultipleReferrals(List<ReferalContactItem> multipleReferrals) {
        this.multipleReferrals = multipleReferrals;
    }
}
