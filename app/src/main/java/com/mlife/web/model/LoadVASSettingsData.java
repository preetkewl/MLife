package com.mlife.web.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by milagro on 1/10/2018.
 */

public class LoadVASSettingsData {

    @SerializedName("names")
    @Expose
    private List<LoadVASSettingsName> names = null;

    @SerializedName("budgets")
    @Expose
    private List<LoadVASSettingsBudget> budgets = null;

    public List<LoadVASSettingsName> getNames() {
        return names;
    }

    public void setNames(List<LoadVASSettingsName> names) {
        this.names = names;
    }

    public List<LoadVASSettingsBudget> getBudgets() {
        return budgets;
    }

    public void setBudgets(List<LoadVASSettingsBudget> budgets) {
        this.budgets = budgets;
    }

}