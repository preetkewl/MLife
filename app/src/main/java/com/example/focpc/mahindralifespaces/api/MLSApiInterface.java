package com.example.focpc.mahindralifespaces.api;

import com.example.focpc.mahindralifespaces.ui.activities.select_contact.ReferUserListItem;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Ramees T on 04-12-2017.
 */

public interface MLSApiInterface {

    @FormUrlEncoded
    @POST("user/addnewUser")
    Call<JsonElement> addNewUser(@Field("user_phone") String user_phone, @Field("user_name") String user_name,
                                 @Field("user_email") String user_email);

    @FormUrlEncoded
    @POST("api_userLoyalieReferral/promote_us")
    Call<JsonElement> promoteContact(@Field("ex_user_id") String ex_user_id, @Field("designation") String designation,
                                     @Field("comments") String comments, @Field("contact_no") String contact_no,
                                     @Field("contact_name") String contact_name, @Field("contact_email") String contact_email,
                                     @Field("institute_type") String institute_type, @Field("institute_name") String institute_name,
                                     @Field("city") String city);

    @FormUrlEncoded
    @POST("api_userLoyalieReferral/reward_info_new")
    Call<JsonElement> getRewardWallet(@Field("user_id") String userId);

    @FormUrlEncoded
    @POST("api_userLoyalieReferral/activeReferralUser")
    Call<JsonElement> getReferalData(@Field("user_id") String userId);

    @POST("apiUserReferral/checkExistingUser")
    Call<JsonElement> checkIfNewuserExists(@Body JsonObject referUserListItem);

    @POST("apiUserReferral/addNewReferralsMobV.3")
    Call<JsonElement> referUsers(@Body JsonObject referUserListItem, @Query("comments") String comment);


}
