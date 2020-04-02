package com.example.focpc.mahindralifespaces.ui.activities.select_contact;


import android.text.TextUtils;

import com.example.focpc.mahindralifespaces.ui.activities.referal_section.UserItem;
import com.example.focpc.mahindralifespaces.utils.MlsApp;
import com.example.focpc.mahindralifespaces.utils.MlsParser;
import com.example.focpc.mahindralifespaces.utils.MlsUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by foc pc on 06-12-2017.
 */

public class SelectContactInteractorImple implements SelectContactInteractor {

    @Override
    public void checkIfAnyUserEligible(final List<ContactItem> contactItemList, final SelectContactListener listener,
                                       String referlId, String referralUserId) {
        UserItem userItem = MlsUtils.getUserItem(MlsApp.getContext());

        Type listType = new TypeToken<List<ContactItem>>() {
        }.getType();
        Gson gson = new GsonBuilder().registerTypeAdapter(listType,
                new ContactSerializer(String.valueOf(userItem.getUser_id()), userItem.getUser_name(),
                        referlId, referralUserId))
                .setPrettyPrinting()
                .create();

        JsonParser parser = new JsonParser();
        JsonObject jsonIn = parser.parse(gson.toJson(contactItemList, listType)).getAsJsonObject();
        MlsApp.getMlsService().checkIfNewuserExists(jsonIn).enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {

                try {
                    if (MlsParser.isDataContained(response, listener)) {
                        JsonObject jsonObj = response.body().getAsJsonObject();
                        List<UserStatusItem> userStatusArray = MlsParser.parseUserStatus(jsonObj.getAsJsonArray("userStatus"));
                        List<UserStatusItem> newUSersArray = new ArrayList<UserStatusItem>();
                        List<UserStatusItem> existingUsers = new ArrayList<UserStatusItem>();
                        List<UserStatusItem> invalidUsers = new ArrayList<UserStatusItem>();
                        for (int i = 0; i < userStatusArray.size(); i++) {
                            UserStatusItem userStatusItem = userStatusArray.get(i);
                            int status = userStatusItem.getUser_status();
                            if (status == 1) {
                                newUSersArray.add(userStatusItem);
                            } else if (status == 2 || status == 3) {
                                existingUsers.add(userStatusItem);
                            } else {
                                invalidUsers.add(userStatusItem);
                            }
                        }
                        listener.userExistCheckResult(newUSersArray.size(), existingUsers.size(),
                                invalidUsers.size(), contactItemList);
                    }

                } catch (Exception e) {
                    listener.onError("Sorry, something went wrong !");
                }

            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                listener.onApiError("Failed to connect to server, please try again !");
            }
        });
    }

    @Override
    public void referUsers(final List<ContactItem> contactItemList, String referalId,
                           String comment, final SelectContactListener listener, String referralUserId) {

        UserItem userItem = MlsUtils.getUserItem(MlsApp.getContext());

        Type listType = new TypeToken<List<ContactItem>>() {
        }.getType();
        Gson gson = new GsonBuilder().registerTypeAdapter(listType,
                new ContactSerializer(String.valueOf(userItem.getUser_id()), userItem.getUser_name(),
                        referalId, referralUserId))
                .setPrettyPrinting()
                .create();

        JsonParser parser = new JsonParser();

        MlsApp.getMlsService().referUsers(parser.parse(gson.toJson(contactItemList, listType)).
                getAsJsonObject(), comment).enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {

                try {
                    if (MlsParser.isDataContained(response, listener)) {
                        JsonObject jsonObj = response.body().getAsJsonObject();
                        List<UserStatusItem> userStatusArray = MlsParser.parseUserStatus(jsonObj.getAsJsonArray("userStatus"));
                        setPhoneBookNames(userStatusArray, contactItemList);
                        listener.onReferSuccess(userStatusArray);
                    }

                } catch (Exception e) {
                    listener.onError("Sorry, something went wrong !");
                }

            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                listener.onApiError("Failed to connect to server, please try again !");
            }
        });

    }



    private void setPhoneBookNames(List<UserStatusItem> userStatusArray, List<ContactItem> contactItems) {
        for (ContactItem contactItem : contactItems) {
            for (UserStatusItem userStatusItem : userStatusArray) {
                if (userStatusItem.getUser_phone().length() < 10 &&
                        contactItem.getNew_user_phone().equalsIgnoreCase(userStatusItem.getUser_phone()))
                    userStatusItem.setUser_name(contactItem.getNew_user_name());
                else if (userStatusItem.getUser_phone().length() > 9 &&
                        contactItem.getNew_user_phone().contains(userStatusItem.getUser_phone()))
                    userStatusItem.setUser_name(contactItem.getNew_user_name());
            }

        }

    }
}
