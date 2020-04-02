package com.example.focpc.mahindralifespaces.ui.activities.refer_now;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.focpc.mahindralifespaces.utils.MlsConstants;
import com.example.focpc.mahindralifespaces.utils.MlsParser;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.lang.ref.WeakReference;

/**
 * Created by foc pc on 18-12-2017.
 */

public class ReferalDataFetchAsync extends AsyncTask<Void, Void, Void> {
    private Context context ;
    private ReferNowListener listener;

    public ReferalDataFetchAsync(Context context,ReferNowListener listener) {
        this.context= context ;
        this.listener = listener;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        String value = "";
        try {
            FileInputStream fis = context.openFileInput(MlsConstants.REFERAL_DATA_SAVED);
            ObjectInputStream is = new ObjectInputStream(fis);
            value = (String) is.readObject();
            is.close();
            fis.close();
            JsonParser parser = new JsonParser();
            JsonObject jsonObject = parser.parse(value).getAsJsonObject();

            listener.onReferalDataFetched(MlsParser.parseRefralItem(jsonObject.get("referralList").getAsJsonArray())
                    , MlsParser.parseLeadsList(jsonObject.get("leads_status_details").getAsJsonArray()),true);

        } catch (Exception e) {
            listener.onNoDataInLocal();
        }finally {
            context = null;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }
}
