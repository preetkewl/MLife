package com.example.focpc.mahindralifespaces.ui.activities.reward_wallet;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by foc pc on 06-12-2017.
 */

public class VisitLeftItem {

    private String program_name;

    private String program_end_date;

    private String vendor_logo;

    private String vendor_name;

    public String getProgramName() {
        return program_name;
    }

    public void setProgramName(String program_name) {
        this.program_name = program_name;
    }

    public String getProgramEndDate() {
        return program_end_date;
    }

    public void setProgramEndDate(String program_end_date) {
        this.program_end_date = program_end_date;
    }

    public String getVendorLogo() {
        return vendor_logo;
    }

    public void setVendorLogo(String vendor_logo) {
        this.vendor_logo = vendor_logo;
    }

    public String getVendorName() {
        return vendor_name;
    }

    public void setVendorName(String vendor_name) {
        this.vendor_name = vendor_name;
    }
}
