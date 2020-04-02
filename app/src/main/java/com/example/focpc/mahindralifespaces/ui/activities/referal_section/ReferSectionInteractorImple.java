package com.example.focpc.mahindralifespaces.ui.activities.referal_section;

import android.app.Activity;

import com.example.focpc.mahindralifespaces.utils.MlsApp;
import com.example.focpc.mahindralifespaces.utils.MlsParser;
import com.example.focpc.mahindralifespaces.utils.MlsUtils;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by foc pc on 14-12-2017.
 */

public class ReferSectionInteractorImple implements ReferSectionInteractor {

    @Override
    public void addNewUser(String mobile, String name, String email, final ReferSectionListener referSectionListener) {
        MlsApp.getMlsService().addNewUser(mobile, name, email).enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                try {
                    if (MlsParser.isDataContained(response, referSectionListener)) {
                        JsonObject jsonObj = response.body().getAsJsonObject();
                        UserItem userItem = MlsParser.parseUser(jsonObj.getAsJsonObject("user"));
                        MlsUtils.setApiToken(jsonObj.getAsJsonObject("data").get("sec_key").getAsString());
                        referSectionListener.onNewUserAdded(userItem);
                    }

                } catch (Exception e) {
                    referSectionListener.onUserAdditionFailed("Sorry, something went wrong !");
                }
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {

                referSectionListener.onApiError("Failed to connect to server, please try again !");

            }
        });

    }
}
