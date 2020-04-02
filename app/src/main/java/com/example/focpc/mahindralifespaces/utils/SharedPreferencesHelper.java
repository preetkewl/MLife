package com.example.focpc.mahindralifespaces.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;


public class SharedPreferencesHelper {

    public static void putBoolean(String key, boolean val, Context mContext) {
        SharedPreferences preferences = PreferenceManager
                .getDefaultSharedPreferences(mContext);
        Editor edit = preferences.edit();
        edit.putBoolean(key, val);
        edit.apply();
    }

    public static void putString(String key, String val, Context mContext) {
        SharedPreferences preferences = PreferenceManager
                .getDefaultSharedPreferences(mContext);
        Editor edit = preferences.edit();
        edit.putString(key, val);
        edit.apply();

    }

    public static void putInt(String key, int val, Context mContext) {
        SharedPreferences preferences = PreferenceManager
                .getDefaultSharedPreferences(mContext);
        Editor edit = preferences.edit();
        edit.putInt(key, val);
        edit.apply();
    }

    public static String getString(String key, String _default, Context mContext) {
        SharedPreferences preferences = PreferenceManager
                .getDefaultSharedPreferences(mContext);
        return preferences.getString(key, _default);
    }


    public static boolean getBoolean(String key, boolean _default, Context mContext) {
        SharedPreferences preferences = PreferenceManager
                .getDefaultSharedPreferences(mContext);
        return preferences.getBoolean(key, _default);
    }

    public static void clearPreferences(Context mContext) {
        SharedPreferences preferences = PreferenceManager
                .getDefaultSharedPreferences(mContext);
        preferences.edit().clear().apply();
    }

    public static int getInt(String key, int _default, Context mContext) {
        SharedPreferences preferences = PreferenceManager
                .getDefaultSharedPreferences(mContext);
        return preferences.getInt(key, _default);
    }
}