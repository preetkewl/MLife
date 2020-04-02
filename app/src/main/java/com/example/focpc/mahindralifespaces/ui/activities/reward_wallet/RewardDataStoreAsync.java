package com.example.focpc.mahindralifespaces.ui.activities.reward_wallet;

import android.content.Context;
import android.os.AsyncTask;

import com.example.focpc.mahindralifespaces.utils.MlsConstants;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/**
 * Created by foc pc on 18-12-2017.
 */

public class RewardDataStoreAsync extends AsyncTask<Void, Void, Void> {
    private String data;
    private Context context;

    public RewardDataStoreAsync(Context context, String data) {
        this.context = context;
        this.data = data;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            FileOutputStream fos = context.openFileOutput(MlsConstants.REWARD_DATA_SAVED, Context.MODE_PRIVATE);
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
