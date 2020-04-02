package com.example.focpc.mahindralifespaces.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.mlife.R;

import com.example.focpc.mahindralifespaces.ui.activities.referal_section.UserItem;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by foc pc on 04-12-2017.
 */

public class MlsUtils {

    public static String getApiToken(Context context) {
        return SharedPreferencesHelper.getString(MlsConstants.API_TOKEN, "", context);
    }

    public static void setApiToken(String apiToken) {
        SharedPreferencesHelper.putString(MlsConstants.API_TOKEN, apiToken, MlsApp.getContext());
    }

    public static void setUserData(UserItem userItem, Context context) {
        SharedPreferencesHelper.putInt(MlsConstants.USER_ID, userItem.getUser_id(), context);
        SharedPreferencesHelper.putString(MlsConstants.USER_NAME, userItem.getUser_name(), context);
        SharedPreferencesHelper.putString(MlsConstants.USER_EMAIL, userItem.getUser_email(), context);
        SharedPreferencesHelper.putString(MlsConstants.USER_MOBILE, userItem.getUser_phone(), context);
    }

    public static int getUserId(Context context) {
        return SharedPreferencesHelper.getInt(MlsConstants.USER_ID, 0, context);
    }

    public static UserItem getUserItem(Context context) {
        return new UserItem(getUserId(context),
                SharedPreferencesHelper.getString(MlsConstants.USER_NAME, "", context),
                SharedPreferencesHelper.getString(MlsConstants.USER_EMAIL, "", context),
                SharedPreferencesHelper.getString(MlsConstants.USER_MOBILE, "", context));

    }

    public static void showErrorSnackbar(final Activity mActivity, final String msg) {
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                View parentLayout = mActivity.getWindow().getDecorView().findViewById(android.R.id.content);
                Snackbar mSnackbar = Snackbar.make(parentLayout, msg, Snackbar.LENGTH_LONG)
                        .setDuration(Snackbar.LENGTH_LONG);
                mSnackbar.getView().setBackgroundColor(mActivity.getResources().getColor(R.color.colorPrimary));
                mSnackbar.setActionTextColor(mActivity.getResources().getColor(R.color.colorAccent));
                TextView textView = mSnackbar.getView().findViewById(android.support.design.R.id.snackbar_text);
                textView.setTextColor(Color.WHITE);
                textView.setTypeface(getProximaRegular(mActivity));
                mSnackbar.show();
            }
        });
    }

    public static void showToast(Context context, String message, boolean isShort) {
        if (isShort) Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        else Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public static boolean isValidEmail(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        return matcher.matches();
    }

    public static void hideSoftKey(Activity mActivity, View view) {
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) mActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static Typeface getProximaRegular(Context context) {
        if (MlsApp.getTypeFaceCache().get(MlsConstants.PROXIMA_NOVA_REGULAR) != null)
            return MlsApp.getTypeFaceCache().get(MlsConstants.PROXIMA_NOVA_REGULAR);
        else {
            Typeface typeface = ResourcesCompat.getFont(context, R.font.proximanova_regular);
            MlsApp.getTypeFaceCache().put(MlsConstants.PROXIMA_NOVA_REGULAR, typeface);
            return typeface;
        }
    }

    public static String convertDateFormat(String input) {
        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        DateFormat outputFormat = new SimpleDateFormat("dd MMM yyyy", Locale.US);
        Date date = new Date();
        try {
            date = inputFormat.parse(input);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return outputFormat.format(date);
    }

    public static void showInWebView(String url, Context context) {
        try {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            context.startActivity(browserIntent);
        } catch (Exception e) {
            showToast(context, "Sorry, unable to open the link", true);
        }
    }

    public static void sendEmailIntent(Context context){
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:" + "to@email.com"));
        PackageManager packageManager = context.getPackageManager();
        List activities = packageManager.queryIntentActivities(intent,
                PackageManager.MATCH_DEFAULT_ONLY);
        if(activities.size() > 0) {
            context.startActivity(Intent.createChooser(intent, "Send mail..."));
        } else {
            showToast(context, "Sorry, No email clients found", true);
        }

    }


}
