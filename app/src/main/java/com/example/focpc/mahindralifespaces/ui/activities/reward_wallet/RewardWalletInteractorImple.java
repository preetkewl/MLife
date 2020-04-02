package com.example.focpc.mahindralifespaces.ui.activities.reward_wallet;

import android.content.Context;
import android.os.AsyncTask;

import com.example.focpc.mahindralifespaces.utils.MlsApp;
import com.example.focpc.mahindralifespaces.utils.MlsParser;
import com.example.focpc.mahindralifespaces.utils.MlsUtils;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by foc pc on 14-12-2017.
 */

public class RewardWalletInteractorImple implements RewardWalletInteractor {

    @Override
    public void getRewardWallet(final Context context, String userId, final RewardWalletListener listener) {

        MlsApp.getMlsService().getRewardWallet(userId).enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {

                try {
                    if(MlsParser.isDataContained(response,listener)){
                        JsonObject jsonObject = response.body().getAsJsonObject();
                        List<RewardDetailsItem> rewardDetailsList = MlsParser.parseRewardWallet(jsonObject.getAsJsonArray("RewardDetails"));
                        List<VisitLeftItem> visitLeftList = MlsParser.parseRewardWalletVisitLeft(jsonObject.getAsJsonArray("visitLeft"));
                        listener.onRewardWalletFetched(rewardDetailsList,visitLeftList,false);
                        new RewardDataStoreAsync(context,jsonObject.toString()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
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
