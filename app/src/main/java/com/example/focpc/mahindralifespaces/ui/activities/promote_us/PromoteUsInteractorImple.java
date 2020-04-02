package com.example.focpc.mahindralifespaces.ui.activities.promote_us;

import com.example.focpc.mahindralifespaces.utils.MlsApp;
import com.example.focpc.mahindralifespaces.utils.MlsParser;
import com.google.gson.JsonElement;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by foc pc on 14-12-2017.
 */

public class PromoteUsInteractorImple implements PromoteUsInteractor {

    @Override
    public void promoteContact(String ex_user_id, String designation, String comments, String contact_no,
                               String contact_name, String contact_email, String institute_type, String institute_name,
                               String city,final PromoteUsListener promoteUsListener) {

        MlsApp.getMlsService().promoteContact(ex_user_id,designation,comments,contact_no,contact_name,contact_email,
                institute_type,institute_name,city).enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                try {
                    if (MlsParser.isDataContained(response, promoteUsListener)) {
                        promoteUsListener.onPromoted();
                    }
                } catch (Exception e){
                    promoteUsListener.onFailedToPromote("Sorry, something went wrong !");
                }

            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                promoteUsListener.onApiError("Failed to connect to server, please try again !");
            }
        });



    }
}
