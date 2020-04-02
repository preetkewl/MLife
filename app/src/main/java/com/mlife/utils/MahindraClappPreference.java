package com.mlife.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by milagro on 9/19/2017.
 */

public class MahindraClappPreference {

    private static MahindraClappPreference mahindraClappPreference;
    private SharedPreferences sharedPreferences;


    public static MahindraClappPreference getMahindraClappPreference() {
        return mahindraClappPreference;
    }

    public static void setMahindraClappPreference(MahindraClappPreference mahindraClappPreference) {
        MahindraClappPreference.mahindraClappPreference = mahindraClappPreference;
    }

    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    public void setSharedPreferences(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public Boolean getNotification() {
        return sharedPreferences.getBoolean("notification", true);
    }

    public void setNotification(Boolean notification) {
        sharedPreferences.edit().putBoolean("notification", notification).commit();
    }

    public Boolean getSound() {
        return sharedPreferences.getBoolean("sound", true);
    }

    public void setSound(Boolean sound) {
        sharedPreferences.edit().putBoolean("sound", sound).commit();
    }

    public static MahindraClappPreference getInstance(Context context) {
        if (mahindraClappPreference == null) {
            mahindraClappPreference = new MahindraClappPreference(context);
        }
        return mahindraClappPreference;
    }

    public MahindraClappPreference(Context context) {
        sharedPreferences = context.getSharedPreferences("YourCustomNamedPreference", Context.MODE_PRIVATE);
    }

    public void saveData(String key, String value) {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.putString(key, value);
        prefsEditor.commit();
    }

    public void clearData() {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        sharedPreferences.edit().putString("Email", "").commit();
        sharedPreferences.edit().putString("Property","").commit();
    }

    public String getData(String key) {
        if (sharedPreferences != null) {
            return sharedPreferences.getString(key, "");
        }
        return "";
    }

    public String getpName() {
        return sharedPreferences.getString("Name", "").toString();
    }

    public void setpName(String pName) {
        sharedPreferences.edit().putString("Name", pName).commit();
    }

    public String getProfileImage() {
        return sharedPreferences.getString("ProfilePicture", "").toString();
    }

    public void setProfileImage(String profilePicture) {
        sharedPreferences.edit().putString("ProfilePicture", profilePicture).commit();
    }

    public String getpEmail() {
        return sharedPreferences.getString("Email", "").toString();
    }

    public void setpEmail(String pEmail) {
        sharedPreferences.edit().putString("Email", pEmail).commit();
    }

    public String getpUserId() {
        return sharedPreferences.getString("UserId", "").toString();
    }

    public void setpUserId(String pUserId) {
        sharedPreferences.edit().putString("UserId", pUserId).commit();
    }

    public String getpToken() {
        return sharedPreferences.getString("Token", "").toString();
    }

    public void setpToken(String pToken) {
        sharedPreferences.edit().putString("Token", pToken).commit();
    }

    public String getpDeviceToken() {
        return sharedPreferences.getString("DeviceToken", "").toString();
    }

    public void setpDeviceToken(String pDeviceToken) {
        sharedPreferences.edit().putString("DeviceToken", pDeviceToken).commit();
    }

    public String getpMobile() {
        return sharedPreferences.getString("Mobile", "").toString();
    }

    public void setpMobile(String pMobile) {
        sharedPreferences.edit().putString("Mobile", pMobile).commit();
    }

    public boolean isPostHandoOverGuide() {
        return sharedPreferences.getBoolean("postHandoOverGuide", true);
    }

    public void setPostHandoOverGuide(boolean postHandoOverGuide) {
        sharedPreferences.edit().putBoolean("postHandoOverGuide", postHandoOverGuide).commit();
    }

    public boolean isPostSaleGuide() {
        return sharedPreferences.getBoolean("postSaleGuide", true);
    }

    public void setPostSaleGuide(boolean postSaleGuide) {
        sharedPreferences.edit().putBoolean("postSaleGuide", postSaleGuide).commit();
    }


    public Boolean getShowWelcome() {
        return sharedPreferences.getBoolean("showWelcome", true);
    }

    public void setShowWelcome(Boolean showWelcome) {
        sharedPreferences.edit().putBoolean("showWelcome", showWelcome).commit();
    }


    public String getpHomeTown() {
        return sharedPreferences.getString("HomeTown", "").toString();
    }

    public void setpHomeTown(String pHomeTown) {
        sharedPreferences.edit().putString("HomeTown", pHomeTown).commit();
    }

    public String getpLivesIn() {
        return sharedPreferences.getString("LivesIn", "").toString();
    }

    public void setpLivesIn(String LivesIn) {
        sharedPreferences.edit().putString("LivesIn", LivesIn).commit();
    }

    public String getpDOB() {
        return sharedPreferences.getString("DOB", "").toString();
    }

    public void setpDOB(String DOB) {
        sharedPreferences.edit().putString("DOB", DOB).commit();
    }

    public String getpAniversary() {
        return sharedPreferences.getString("Aniversary", "").toString();
    }

    public void setpAniversary(String Aniversary) {
        sharedPreferences.edit().putString("Aniversary", Aniversary).commit();
    }

    public String getpCompany() {
        return sharedPreferences.getString("Company", "").toString();
    }

    public void setpCompany(String Company) {
        sharedPreferences.edit().putString("Company", Company).commit();
    }

    public String getpRole() {
        return sharedPreferences.getString("Role", "").toString();
    }

    public void setpRole(String Aniversary) {
        sharedPreferences.edit().putString("Role", Aniversary).commit();
    }

    public String getpCompanyLocation() {
        return sharedPreferences.getString("CompanyLocation", "").toString();
    }

    public void setpCompanyLocation(String CompanyLocation) {
        sharedPreferences.edit().putString("CompanyLocation", CompanyLocation).commit();
    }

    public String getpIntrest() {
        return sharedPreferences.getString("Intrest", "").toString();
    }

    public void setpIntrest(String Intrest) {
        sharedPreferences.edit().putString("Intrest", Intrest).commit();
    }

    public String getpCompanyType() {
        return sharedPreferences.getString("CompanyType", "").toString();
    }

    public void setpCompanyType(String CompanyType) {
        sharedPreferences.edit().putString("CompanyType", CompanyType).commit();
    }

    public String getUsername() {
        return sharedPreferences.getString("Username", "").toString();
    }

    public void setUsername(String Username) {
        sharedPreferences.edit().putString("Username", Username).commit();
    }

    public boolean getRemember() {
        return sharedPreferences.getBoolean("Remember", false);
    }

    public void setRemember(boolean Remember) {
        sharedPreferences.edit().putBoolean("Remember", Remember).commit();
    }

    public boolean getCapcha() {
        return sharedPreferences.getBoolean("Capcha", false);
    }

    public void setCapcha(boolean Capcha) {
        sharedPreferences.edit().putBoolean("Capcha", Capcha).commit();
    }



}
