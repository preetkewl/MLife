package com.mlife.activities;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.mlife.R;
import com.mlife.utils.AlertDialog;
import com.mlife.utils.Constants;
import com.mlife.utils.DialogProgressBar;
import com.mlife.utils.MahindraClappPreference;
import com.mlife.utils.Validations;
import com.mlife.web.api.Service;
import com.mlife.web.holder.DataHolder;
import com.mlife.web.holder.Response.ObjectLoginOTPResponse;
import com.mlife.web.holder.Response.ObjectLoginResponse;

import java.util.Locale;
import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActivityLoginOTP extends AppCompatActivity implements Observer, DataHolder {

    private Service service = new Service();
    private Validations validations = new Validations();
    private static String deviceToken = "";
    MahindraClappPreference mahindraClappPreference;
    private CountDownTimer mCountDownTimer;
    String data, email;
    DialogProgressBar progressBar = new DialogProgressBar();
    private boolean mTimerRunning;

    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;
    private static final long START_TIME_IN_MILLIS = 60000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_otp);
        ButterKnife.bind(this);
        deviceToken = FirebaseInstanceId.getInstance().getToken();
        objectLoginResponse.addObserver(this);
        ooObjectLoginOTPResponse.addObserver(this);
        mahindraClappPreference = new MahindraClappPreference(this);
        startTimer();
        tvReSend.setClickable(false);
        data = getIntent().getStringExtra("data");
        email = getIntent().getStringExtra("email");
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @BindView(R.id.tvReSend)
    TextView tvReSend;

    @BindView(R.id.cb_TC)
    CheckBox checkBox;

    @BindView(R.id.etOTP)
    EditText etOTP;

    @OnClick(R.id.btnLogin)
    public void onClick() {
        boolean isCheck = true;
        if (etOTP.getText().length() < 4) {
            isCheck = false;
            AlertDialog alertDialog = new AlertDialog();
            alertDialog.AlertDialogWithMessage(ActivityLoginOTP.this, "Please Enter Valid OTP", "Alert");
            return;
        }

        if (!checkBox.isChecked()) {
            isCheck = false;
            AlertDialog alertDialog = new AlertDialog();
            alertDialog.AlertDialogWithMessage(ActivityLoginOTP.this, "Please Accept Terms and Conditions", "Alert");
            return;
        }

        progressBar.showProgressBar(ActivityLoginOTP.this);
        service.verifyOTP(etOTP.getText().toString(), data, deviceToken, "Android", email);
    }

    @OnClick(R.id.tv_TC)
    public void onTCClick() {
        startActivity(new Intent(this, ActivityWebView.class).putExtra("Title", "Terms & Conditions"));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        objectLoginResponse.deleteObservers();
        oObjectLoginOTPResponse.deleteObservers();
        objectGetKey.deleteObservers();
    }

    @Override
    public void update(Observable o, Object arg) {
        progressBar.hideProgressBar();

        if (o instanceof ObjectLoginResponse) {
            if (objectLoginResponse.getLoginResponse().getSuccess()) {
                mahindraClappPreference.setpName(objectLoginResponse.getLoginResponse().getData().getUserFullName().toUpperCase());
                mahindraClappPreference.setpEmail(objectLoginResponse.getLoginResponse().getData().getUserEmail());
                mahindraClappPreference.setpUserId(objectLoginResponse.getLoginResponse().getData().getUserId());
                mahindraClappPreference.setpToken(objectLoginResponse.getLoginResponse().getData().getToken());
                mahindraClappPreference.setpDeviceToken(deviceToken);
                mahindraClappPreference.setProfileImage(Constants.baseUrl + objectLoginResponse.getLoginResponse().getData().getImagePath());
                mahindraClappPreference.setpMobile(objectLoginResponse.getLoginResponse().getData().getMobile());
                Toast.makeText(getBaseContext(), objectLoginResponse.getLoginResponse().getMessage(), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ActivityLoginOTP.this, ActivityChangeProperty.class));
                finishAffinity();
            } else {
                Toast.makeText(getBaseContext(), objectLoginResponse.getLoginResponse().getMessage(), Toast.LENGTH_SHORT).show();
                data = objectLoginResponse.getLoginResponse().getDataKey();
                if (objectLoginResponse.getLoginResponse().getAction().equals("showCaptcha")) {
                    mahindraClappPreference.setCapcha(true);
                }
            }
        } else if (o instanceof ObjectLoginOTPResponse) {
            if (ooObjectLoginOTPResponse.getLoginResponse().getSuccess()) {
                data = ooObjectLoginOTPResponse.getLoginResponse().getData();
            }
        }
    }


    private void startTimer() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();

            }

            @Override
            public void onFinish() {
                tvReSend.setClickable(true);
                mTimeLeftInMillis = START_TIME_IN_MILLIS;
                tvReSend.setText("Resend OTP");
            }
        }.start();
        mTimerRunning = true;
    }


    @OnClick(R.id.tvReSend)
    public void onResendClick() {
        startTimer();
        tvReSend.setClickable(false);
        service.loginOTP(email, deviceToken, "Android");
    }

    private void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;
        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        tvReSend.setText("Resend In " + timeLeftFormatted);
    }

}