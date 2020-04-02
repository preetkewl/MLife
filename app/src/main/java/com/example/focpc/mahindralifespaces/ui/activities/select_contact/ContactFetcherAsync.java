package com.example.focpc.mahindralifespaces.ui.activities.select_contact;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.text.TextUtils;

import com.example.focpc.mahindralifespaces.utils.MlsConstants;
import com.example.focpc.mahindralifespaces.utils.MlsParser;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Created by user on 03-11-2017.
 */

public class ContactFetcherAsync extends AsyncTask<Void, Void, Void> {
    private Context context;
    private ArrayList<ContactItem> tempContactHolder;
    private ArrayList<ContactItem> tempContactHolderCopy;
    private SelectContactListener listener;

    public ContactFetcherAsync(Context context, SelectContactListener listener) {
        this.context = context;
        tempContactHolder = new ArrayList<>();
        tempContactHolderCopy = new ArrayList<>();
        this.listener = listener;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        if (context != null) {
            ContentResolver contentResolver = context.getContentResolver();

            Uri uri = ContactsContract.Contacts.CONTENT_URI;

            String[] projection = new String[]{
                    ContactsContract.Contacts._ID,
                    ContactsContract.Contacts.DISPLAY_NAME,
                    ContactsContract.Contacts.HAS_PHONE_NUMBER
            };
            Cursor cursor = contentResolver.query(
                    uri,
                    projection,
                    null,
                    null,
                    ContactsContract.Contacts.DISPLAY_NAME + " ASC"
            );

            if (cursor != null && cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    if (Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
                        String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                        String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                        ContactItem contactItem = new ContactItem();
                        contactItem.setNew_user_name(name);

                        Cursor phoneCursor = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                null,
                                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=?",
                                new String[]{id},
                                null
                        );


                        if (phoneCursor != null && phoneCursor.getCount() > 0) {
                            while (phoneCursor.moveToNext()) {
                                String phNoIn = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                                String phNo = phNoIn.replace("+91", "");
                                if (TextUtils.isEmpty(contactItem.getNew_user_phone())) {
                                    contactItem.setNew_user_phone(phNo.replaceAll("[^0-9]", ""));
                                    contactItem.getPhnNumbers().add(phNo.replaceAll("[^0-9]", ""));
                                } else {
                                    if (!contactItem.getPhnNumbers().contains(phNo.replaceAll("[^0-9]", ""))) {
                                        contactItem.getPhnNumbers().add(phNo.replaceAll("[^0-9]", ""));
                                    }
                                }


                            }
                            phoneCursor.close();
                        }

                        Cursor emailCur = contentResolver.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI, null,
                                ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ?", new String[]{id}, null);
                        while (emailCur != null && emailCur.moveToNext()) {
                            String emailContact = emailCur.getString(emailCur.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
                            contactItem.setNew_user_email(emailContact);
                        }
                        if (emailCur != null) emailCur.close();

                        if (!tempContactHolder.contains(contactItem) && contactItem.getNew_user_phone() != null) {
                            tempContactHolder.add(contactItem);
                            try {
                                tempContactHolderCopy.add((ContactItem) contactItem.clone());
                            } catch (CloneNotSupportedException e) {
                                e.printStackTrace();
                            }
                        }


                    }

                }
                cursor.close();
                try {
                    FileOutputStream fos = context.openFileOutput(MlsConstants.CONTACT_SAVED, Context.MODE_PRIVATE);
                    ObjectOutputStream os = null;
                    os = new ObjectOutputStream(fos);
                    os.writeObject(MlsParser.writeContactsToString(tempContactHolderCopy));
                    os.close();
                    fos.close();

                } catch (Exception e) {
                    e.printStackTrace();
                    if (listener != null) listener.onContactfetchFail();
                } finally {
                    context = null;
                }
            }
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if (listener != null) listener.onContctFetched(tempContactHolder, tempContactHolderCopy);
    }
}
