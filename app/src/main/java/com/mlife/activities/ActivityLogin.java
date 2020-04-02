package com.mlife.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.iid.FirebaseInstanceId;

import com.mlife.utils.AlertDialog;
import com.mlife.utils.Constants;
import com.mlife.utils.Encyrption;
import com.mlife.web.api.Service;
import com.mlife.R;
import com.mlife.utils.MahindraClappPreference;
import com.mlife.utils.DialogProgressBar;
import com.mlife.utils.Validations;
import com.mlife.web.holder.DataHolder;
import com.mlife.web.holder.Response.ObjectLoginResponse;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActivityLogin extends AppCompatActivity implements Observer, DataHolder {

    Vibrator vibe;
    Animation shake;
    String capcha = "";
    static String deviceToken = "";
    Service service = new Service();
    Encyrption encyrption = new Encyrption();
    DialogProgressBar progressBar = new DialogProgressBar();
    Validations validations = new Validations();
    MahindraClappPreference mahindraClappPreference;
    private String mSiteKey = "6LdBhkYUAAAAAAvxbZYfDKxrRX6e9_aCq8waUtO4";

    @BindView(R.id.ll)
    LinearLayout ll;

    @BindView(R.id.et_Email)
    EditText et_Email;

    @BindView(R.id.et_Password)
    EditText et_Password;

    @BindView(R.id.btn_Login)
    Button btn_Login;

    @BindView(R.id.btn_SignUp)
    Button btn_SignUp;

    @BindView(R.id.cb_TC)
    CheckBox checkBox;

    @BindView(R.id.cb_RememberUsername)
    CheckBox cb_RememberUsername;

    @OnClick(R.id.tv_TC)
    public void onClick() {
        startActivity(new Intent(this, ActivityWebView.class).putExtra("Title", "Terms & Conditions"));
    }


    @Override
    protected void onResume() {
        super.onResume();
        FirebaseAnalytics mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle params = new Bundle();
        params.putString(FirebaseAnalytics.Param.ITEM_NAME, "Login Screen Android");
        mFirebaseAnalytics.setCurrentScreen(this, "Login Screen Android", null);
        mFirebaseAnalytics.logEvent("Login_Screen_Android", params);
    }


    @OnClick(R.id.btn_Login)
    public void onLoginClick(View view) {

        boolean isCheck = true;
        et_Email.setError(null);
        et_Password.setError(null);

        if (!validations.emailAndPhoneValidation(et_Email.getText().toString())) {
            isCheck = false;
            vibe.vibrate(200);
            et_Email.requestFocus();
            et_Email.startAnimation(shake);
            et_Email.setError("Not Valid Input");
        } else if (!validations.passwordValidation(et_Password.getText().toString())) {
            isCheck = false;
            vibe.vibrate(200);
            et_Password.requestFocus();
            et_Password.startAnimation(shake);
            et_Password.setError("Not Valid Input");
        } else if (!checkBox.isChecked()) {
            isCheck = false;
            AlertDialog alertDialog = new AlertDialog();
            alertDialog.AlertDialogWithMessage(ActivityLogin.this, "Please Accept Terms and Conditions", "Alert");
        } else if (mahindraClappPreference.getCapcha()) {
            showCapcha();
        } else if (isCheck) {
            btn_Login.setClickable(false);
            getToken();
        }
    }

    private void getToken() {
        if (getIntent().getExtras() != null) {
            for (String key : getIntent().getExtras().keySet()) {
                String value = getIntent().getExtras().getString(key);
            }
        }

        try {
            if (deviceToken.equals("")) {
                try {
                    deviceToken = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
                } catch (Exception e) {
                }
            }
        } catch (Exception e) {
            deviceToken = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        }
        progressBar.showProgressBar(ActivityLogin.this);
//        service.getKeys(deviceToken, "Android");
    }

    private void showCapcha() {
        SafetyNet.getClient(this).verifyWithRecaptcha(mSiteKey)
                .addOnSuccessListener(this, new OnSuccessListener<SafetyNetApi.RecaptchaTokenResponse>() {
                    @Override
                    public void onSuccess(SafetyNetApi.RecaptchaTokenResponse response) {
                        if (!response.getTokenResult().isEmpty()) {
                            capcha = response.getTokenResult();
                            btn_Login.setClickable(false);
                            mahindraClappPreference.setCapcha(false);
                            getToken();
                        }
                    }
                })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        if (e instanceof ApiException) {
                            Toast.makeText(ActivityLogin.this, "Captcha validation failed, Try again later", Toast.LENGTH_SHORT).show();
                            ApiException apiException = (ApiException) e;
                            Log.d("Error", "Error message: " + CommonStatusCodes.getStatusCodeString(apiException.getStatusCode()));
                        } else {
                            Log.d("Error", "Unknown type of error: " + e.getMessage());
                        }
                    }
                });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__login);
        deviceToken = FirebaseInstanceId.getInstance().getToken();
        ButterKnife.bind(this);
//        AnalyticsApplication application = (AnalyticsApplication) getApplication();
//        Tracker mTracker = application.getDefaultTracker();
//        mTracker.enableAdvertisingIdCollection(true);
//        mTracker.setScreenName("Android-LoginPage");
//        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
        ll.bringToFront();
        objectLoginResponse.addObserver(this);
        objectGetKey.addObserver(this);
        mahindraClappPreference = MahindraClappPreference.getInstance(getApplicationContext());
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        shake = AnimationUtils.loadAnimation(ActivityLogin.this, R.anim.shake);
        vibe = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        if (mahindraClappPreference.getRemember()) {
            cb_RememberUsername.setChecked(true);
            et_Email.setText(mahindraClappPreference.getUsername());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        objectLoginResponse.deleteObservers();
        objectGetKey.deleteObservers();
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof ObjectLoginResponse) {
            btn_Login.setClickable(true);
            progressBar.hideProgressBar();

            if (cb_RememberUsername.isChecked()) {
                mahindraClappPreference.setUsername(et_Email.getText().toString().trim());
                mahindraClappPreference.setRemember(true);
            } else {
                mahindraClappPreference.setRemember(false);
            }

            if (objectLoginResponse.getLoginResponse().getSuccess()) {
                mahindraClappPreference.setpName(objectLoginResponse.getLoginResponse().getData().getUserFullName().toUpperCase());
                mahindraClappPreference.setpEmail(objectLoginResponse.getLoginResponse().getData().getUserEmail());
                mahindraClappPreference.setpUserId(objectLoginResponse.getLoginResponse().getData().getUserId());
                mahindraClappPreference.setpToken(objectLoginResponse.getLoginResponse().getData().getToken());
                mahindraClappPreference.setpDeviceToken(deviceToken);
                mahindraClappPreference.setProfileImage(Constants.baseUrl + objectLoginResponse.getLoginResponse().getData().getImagePath());
                mahindraClappPreference.setpMobile(objectLoginResponse.getLoginResponse().getData().getMobile());
//                AnalyticsApplication application = (AnalyticsApplication) getApplication();
//                Tracker mTracker = application.getDefaultTracker();
//                mTracker.setScreenName("Android-LoginSuccess");
//                mTracker.send(new HitBuilders.ScreenViewBuilder().build());
                Toast.makeText(getBaseContext(), objectLoginResponse.getLoginResponse().getMessage(), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ActivityLogin.this, ActivityChangeProperty.class));
                finishAffinity();
            } else {
                Log.e("ERROR", objectLoginResponse.getLoginResponse().getMessage());
                Toast.makeText(getBaseContext(), objectLoginResponse.getLoginResponse().getMessage(), Toast.LENGTH_SHORT).show();
                if (objectLoginResponse.getLoginResponse().getAction().equals("showCaptcha")) {
                    mahindraClappPreference.setCapcha(true);
                }
            }
        } else {
            if (objectGetKey.getGetKeyResponse().getSuccess()) {
                service.login(et_Email.getText().toString(), encyrption.trytest(objectGetKey.getGetKeyResponse().getValue1().toString(), et_Password.getText().toString().trim(), objectGetKey.getGetKeyResponse().getValue2().toString()), deviceToken, "Android", "v3", capcha);
            } else {
                btn_Login.setClickable(true);
                progressBar.hideProgressBar();
                Toast.makeText(getBaseContext(), objectGetKey.getGetKeyResponse().getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @OnClick(R.id.btn_ForgotPassword)
    public void forgotPassword() {
        startActivity(new Intent(ActivityLogin.this, ActivityForgotPassword.class));
    }

}