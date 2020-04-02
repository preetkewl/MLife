package com.example.focpc.mahindralifespaces.ui.activities.select_contact;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by foc pc on 04-12-2017.
 */

public class ContactSerializer implements JsonSerializer<List<ContactItem>> {
    private String userId,userName,referral_id,referral_user_id;

    public ContactSerializer(String userId, String userName,String referral_id,String referral_user_id) {
        this.userId = userId;
        this.userName = userName;
        this.referral_id = referral_id;
        this.referral_user_id = referral_user_id;

    }

    @Override
    public JsonElement serialize(List<ContactItem> src , Type typeOfSrc, JsonSerializationContext context) {
        JsonArray jsonContacts = new JsonArray();
        for (ContactItem contactItem : src) {
            JsonObject jsonObject = new JsonObject();
            if (contactItem.getNew_user_name() != null)
                jsonObject.addProperty("new_user_name" ,contactItem.getNew_user_name());
            else jsonObject.addProperty("new_user_name" ,"");
            if (contactItem.getNew_user_phone() != null)
                jsonObject.addProperty("new_user_phone" , contactItem.getNew_user_phone());
            else jsonObject.addProperty("new_user_phone" , "");
            if (contactItem.getNew_user_email() != null)
                jsonObject.addProperty("new_user_email" , contactItem.getNew_user_email());
            else jsonObject.addProperty("new_user_email" , "");
            jsonObject.addProperty("ex_user_id" , userId);
            jsonObject.addProperty("ex_user_name" , userName);
            jsonObject.addProperty("referral_id" , referral_id);
            jsonObject.addProperty("referral_user_id" ,referral_user_id );
            jsonContacts.add(jsonObject);
        }
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("multipleReferrals",jsonContacts);

        return jsonObject;
    }
}
