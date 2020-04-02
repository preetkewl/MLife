package com.example.focpc.mahindralifespaces.ui.activities.select_contact;

import android.content.Context;
import android.os.AsyncTask;

import com.example.focpc.mahindralifespaces.utils.MlsConstants;
import com.example.focpc.mahindralifespaces.utils.MlsParser;

import org.json.JSONArray;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by foc pc on 06-12-2017.
 */

public class ReadContactAsync extends AsyncTask<Void, Void, Void> {
    private Context context;
    private List<ContactItem> contactItemList = new ArrayList<>();
    private ArrayList<ContactItem> tempContactHolderCopy;
    private SelectContactListener selectContactListener;

    public ReadContactAsync(Context context, SelectContactListener selectContactListener) {
        this.context = context;
        this.selectContactListener = selectContactListener;
        tempContactHolderCopy = new ArrayList<>();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        String value = "";
        try {
            FileInputStream fis = context.openFileInput(MlsConstants.CONTACT_SAVED);
            ObjectInputStream is = new ObjectInputStream(fis);
            value = (String) is.readObject();
            is.close();
            fis.close();
            JSONArray jsonArray = new JSONArray(value);
            contactItemList.clear();
            contactItemList.addAll(MlsParser.parseContacts(jsonArray));

            for (ContactItem contactItem : contactItemList) {
                tempContactHolderCopy.add((ContactItem) contactItem.clone());
            }
        } catch (Exception e) {
            selectContactListener.onNoContactsInFile();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if (contactItemList.isEmpty()) {
            selectContactListener.onNoContactsInFile();
            context = null;
        } else {
            new ContactFetcherAsync(context, null).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            selectContactListener.onContctFetched(contactItemList, tempContactHolderCopy);
        }
    }
}
