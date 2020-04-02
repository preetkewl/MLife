package com.example.focpc.mahindralifespaces.ui.activities.refer_now;

import android.content.Context;
import android.os.AsyncTask;

import com.example.focpc.mahindralifespaces.utils.BaseListener;
import com.example.focpc.mahindralifespaces.utils.MlsConstants;
import com.google.gson.Gson;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * Created by foc pc on 18-12-2017.
 */

public class ReferalDataStoreAsync extends AsyncTask<Void, Void, Void> {
    private String data;
    private Context context;

    public ReferalDataStoreAsync(Context context, String data) {
        this.context = context;
        this.data = data;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            FileOutputStream fos = context.openFileOutput(MlsConstants.REFERAL_DATA_SAVED, Context.MODE_PRIVATE);
            ObjectOutputStream os = null;
            os = new ObjectOutputStream(fos);
            os.writeObject(data);
            os.close();
            fos.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            context = null;
        }
        return null;
    }

}
