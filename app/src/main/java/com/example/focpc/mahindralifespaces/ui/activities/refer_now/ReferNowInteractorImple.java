package com.example.focpc.mahindralifespaces.ui.activities.refer_now;

import android.content.Context;
import android.os.AsyncTask;

import com.example.focpc.mahindralifespaces.utils.MlsApp;
import com.example.focpc.mahindralifespaces.utils.MlsParser;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by foc pc on 15-12-2017.
 */

public class ReferNowInteractorImple implements ReferNowInteractor {

    @Override
    public void getReferalData(String userId,final Context context, final ReferNowListener listener) {
        MlsApp.getMlsService().getReferalData(userId).enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                try {
                    if (MlsParser.isDataContained(response, listener)) {
                        JsonObject jsonObject = response.body().getAsJsonObject();
                        List<ReferalItem> referalItemList = MlsParser.parseRefralItem(jsonObject.get("referralList").getAsJsonArray());
                        List<LeadItem> leadItems = MlsParser.parseLeadsList(jsonObject.get("leads_status_details").getAsJsonArray());
                        listener.onReferalDataFetched(referalItemList, leadItems,false);
                        new ReferalDataStoreAsync(context,jsonObject.toString()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
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
}
