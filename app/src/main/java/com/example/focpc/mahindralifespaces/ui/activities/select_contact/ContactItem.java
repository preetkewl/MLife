package com.example.focpc.mahindralifespaces.ui.activities.select_contact;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by foc pc on 04-12-2017.
 */

public class ContactItem implements Cloneable{

    private String new_user_name;
    private String new_user_phone;
    private String new_user_email;
    private transient int presentSelection;
    private transient boolean isSelected;
    private String ex_user_id;
    private String ex_user_name;
    private String referral_id;
    private String referral_user_id;
    private List<String> phnNumbers = new ArrayList<>();

    public ContactItem(String new_user_name, String new_user_phone, String new_user_email) {
        this.new_user_name = new_user_name;
        this.new_user_phone = new_user_phone;
        this.new_user_email = new_user_email;
    }

    public ContactItem() {
    }

    public String getNew_user_name() {
        return new_user_name;
    }

    public void setNew_user_name(String new_user_name) {
        this.new_user_name = new_user_name;
    }

    public String getNew_user_phone() {
        return new_user_phone;
    }

    public void setNew_user_phone(String new_user_phone) {
        this.new_user_phone = new_user_phone;
    }

    public String getNew_user_email() {
        return new_user_email;
    }

    public void setNew_user_email(String new_user_email) {
        this.new_user_email = new_user_email;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getEx_user_id() {
        return ex_user_id;
    }

    public void setEx_user_id(String ex_user_id) {
        this.ex_user_id = ex_user_id;
    }

    public String getEx_user_name() {
        return ex_user_name;
    }

    public void setEx_user_name(String ex_user_name) {
        this.ex_user_name = ex_user_name;
    }

    public String getReferral_id() {
        return referral_id;
    }

    public void setReferral_id(String referral_id) {
        this.referral_id = referral_id;
    }

    public String getReferral_user_id() {
        return referral_user_id;
    }

    public void setReferral_user_id(String referral_user_id) {
        this.referral_user_id = referral_user_id;
    }

    public List<String> getPhnNumbers() {
        return phnNumbers;
    }

    public void setPhnNumbers(List<String> phnNumbers) {
        this.phnNumbers = phnNumbers;
    }

    public int getPresentSelection() {
        return presentSelection;
    }

    public void setPresentSelection(int presentSelection) {
        this.presentSelection = presentSelection;
    }

    @Override public Object clone() throws CloneNotSupportedException{ return super.clone(); }
}
