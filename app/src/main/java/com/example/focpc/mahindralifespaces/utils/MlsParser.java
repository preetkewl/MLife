package com.example.focpc.mahindralifespaces.utils;


import com.example.focpc.mahindralifespaces.ui.activities.refer_now.LeadItem;
import com.example.focpc.mahindralifespaces.ui.activities.refer_now.ReferalItem;
import com.example.focpc.mahindralifespaces.ui.activities.referal_section.UserItem;
import com.example.focpc.mahindralifespaces.ui.activities.reward_wallet.RewardDetailsItem;
import com.example.focpc.mahindralifespaces.ui.activities.reward_wallet.VisitLeftItem;
import com.example.focpc.mahindralifespaces.ui.activities.select_contact.ContactItem;
import com.example.focpc.mahindralifespaces.ui.activities.select_contact.UserStatusItem;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Response;

/**
 * Created by foc pc on 06-12-2017.
 */

public class MlsParser {
    private static Gson gson;

    public static List<ContactItem> parseContacts(JSONArray json) {
        if (gson == null) gson = new Gson();
        Type listType = new TypeToken<List<ContactItem>>() {
        }.getType();
        return gson.fromJson(json.toString(), listType);
    }

    public static boolean isDataContained(Response<JsonElement> response, BaseListener listener) throws Exception {
        if (response.isSuccessful()) {
            JsonObject jsonObj = response.body().getAsJsonObject();
            JsonObject successObj = jsonObj.get("success").getAsJsonObject();
            if (successObj.get("success").getAsString().equalsIgnoreCase("True")) {
                return true;
            } else if (successObj.has("message") &&
                    successObj.get("message").getAsString().equalsIgnoreCase("Authentication Failed")) {
                listener.onSessionExpired();
                return false;
            } else {
                listener.onApiError(successObj.get("message").getAsString());
                return false;
            }
        } else {
            listener.onApiError("Failed to connect to server, please try again !");
            return false;
        }
    }

    public static UserItem parseUser(JsonObject json) {
        if (gson == null) gson = new Gson();
        return gson.fromJson(json , UserItem.class);
    }

    public static List<RewardDetailsItem> parseRewardWallet(JsonArray rewardDetails) {
        if (gson == null) gson = new Gson();
        Type listType = new TypeToken<List<RewardDetailsItem>>() {
        }.getType();
        return gson.fromJson(rewardDetails, listType);
    }

    public static List<VisitLeftItem> parseRewardWalletVisitLeft(JsonArray rewardDetails) {
        if (gson == null) gson = new Gson();
        Type listType = new TypeToken<List<VisitLeftItem>>() {
        }.getType();
        return gson.fromJson(rewardDetails, listType);
    }

    public static List<ReferalItem> parseRefralItem (JsonArray referalArray) {
        if (gson == null) gson = new Gson();
        Type listType = new TypeToken<List<ReferalItem>>() {
        }.getType();
        return gson.fromJson(referalArray, listType);
    }

    public static List<LeadItem> parseLeadsList (JsonArray leadsArray) {
        if (gson == null) gson = new Gson();
        Type listType = new TypeToken<List<LeadItem>>() {
        }.getType();
        return gson.fromJson(leadsArray, listType);
    }

    public static List<UserStatusItem> parseUserStatus (JsonArray statusArray) {
        if (gson == null) gson = new Gson();
        Type listType = new TypeToken<List<UserStatusItem>>() {
        }.getType();
        return gson.fromJson(statusArray, listType);
    }

    public static String writeContactsToString(List <ContactItem> contactItems){
        if (gson == null) gson = new Gson();
        return gson.toJsonTree(contactItems).getAsJsonArray().toString();
    }
}
