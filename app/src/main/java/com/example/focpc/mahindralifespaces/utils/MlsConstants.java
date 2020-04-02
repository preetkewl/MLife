package com.example.focpc.mahindralifespaces.utils;

import android.content.Intent;

/**
 * Created by foc pc on 04-12-2017.
 */

public class MlsConstants {

    public static final String BASE_URL = "http://mahindra.loyalie.in:8080/Mahindra.Loyalie/";

    public static String IMG_BASE_URL = "https://s3-ap-southeast-1.amazonaws.com/mahindra.loyalie/";

    public static final String API_TOKEN = "api_token";
    public static final String CONTACT_SAVED = "saved_contacts";
    public static final String REFERAL_DATA_SAVED = "referal_data_saved";
    public static final String REWARD_DATA_SAVED = "reward_data_saved";

    public static final int CONTACT_PICK_REQUEST = 100;
    public static final int CONTACT_PICK_RESULT = 101;
    public static final int REFER_NOW_REQUEST = 102;
    public static final int REFER_NOW_RESULT = 103;
    public static final int GET_CONTACTS_FLAG = 104 ;
    public static final int PLACES_PICK_REQUEST = 105 ;

    public static final String PICKED_NAME = "picked_name";
    public static final String PICKED_NUMBER = "picked_number";
    public static final String PROXIMA_NOVA_REGULAR = "proximanova_regular";
    public static final String INSTITUTION_TYPE = "institution_type";
    public static final String REFERAL_ID = "referal_id";
    public static final String REFERAL_PRJCT_NAME = "referal_prjct_name";
    public static final String REFERAL_USER_ID = "referral_user_id";

    public static final String USER_ID = "user_id";
    public static final String USER_EMAIL = "user_email";
    public static final String USER_NAME = "user_name";
    public static final String USER_MOBILE = "user_mobile";

    public static int FRESH_FLAG = Intent.FLAG_ACTIVITY_CLEAR_TOP
            | Intent.FLAG_ACTIVITY_CLEAR_TASK
            | Intent.FLAG_ACTIVITY_SINGLE_TOP
            | Intent.FLAG_ACTIVITY_NEW_TASK;

}
