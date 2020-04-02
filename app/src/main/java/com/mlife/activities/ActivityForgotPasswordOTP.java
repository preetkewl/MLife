package com.mlife.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mlife.activities.Extras.Activity_ThankYou;
import com.mlife.R;
import com.mlife.utils.DialogProgressBar;
import com.mlife.web.api.Service;
import com.mlife.web.holder.Response.ObjectForgotPassword;
import com.mlife.web.holder.Response.ObjectForgotPasswordVerify;

import java.util.Locale;
import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.mlife.web.holder.DataHolder.objectForgotPassword;
import static com.mlife.web.holder.DataHolder.objectForgotPasswordVerify;

public class ActivityForgotPasswordOTP extends AppCompatActivity implements Observer {

    String email, deviceToken;
    private boolean mTimerRunning;
    private CountDownTimer mCountDownTimer;
    DialogProgressBar progressBar = new DialogProgressBar();
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;
    private static final long START_TIME_IN_MILLIS = 600000;

    @BindView(R.id.tv_timer)
    TextView mTextViewCountDown;

    @BindView(R.id.et_OTP)
    EditText et_OTP;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        objectForgotPasswordVerify.deleteObservers();
        objectForgotPassword.deleteObservers();
    }

    @OnClick(R.id.tv_resendOTP)
    public void resendOTP() {
        progressBar.showProgressBar(ActivityForgotPasswordOTP.this);
        new Service().forgotPassword(email, deviceToken, "Android");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__forgot_password_otp);
        ButterKnife.bind(this);
        objectForgotPasswordVerify.addObserver(this);
        objectForgotPassword.addObserver(this);
        try {
            startTimer();
            deviceToken = getIntent().getStringExtra("Token");
            email = getIntent().getStringExtra("Email");
        } catch (Exception ex) {
            ex.printStackTrace();
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
                mTextViewCountDown.setText("OTP expired");
            }
        }.start();
        mTimerRunning = true;
    }

    private void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;
        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        mTextViewCountDown.setText("OTP expire in " + timeLeftFormatted);
    }

    @OnClick(R.id.iv_Back_ManageDitePlan)
    public void crossClick() {
        finish();
    }

    @OnClick(R.id.btn_Send)
    public void submitOTP() {
        progressBar.showProgressBar(ActivityForgotPasswordOTP.this);
        if (et_OTP.getText().toString().trim().length() == 6) {
            new Service().forgotPassword_verify(email, deviceToken, "v3", "Android", et_OTP.getText().toString().trim());
        } else {
            et_OTP.setError("Invalid OTP");
        }
    }

    @Override
    public void update(Observable observable, Object o) {
        if (observable instanceof ObjectForgotPasswordVerify) {
            progressBar.hideProgressBar();
            if (((ObjectForgotPasswordVerify) observable).getForgotPasswordVerifyResponse().getSuccess()) {
                startActivity(new Intent(ActivityForgotPasswordOTP.this, Activity_ThankYou.class).putExtra("Title", "Forgot password").putExtra("Heading", "Thank You").putExtra("Detail", "A reset password link has been sent to your registered e-mail ID. Please check your e-mail and generate new password.").putExtra("Image", R.mipmap.verifyaccounticon).putExtra("callBack", "ForgotPassword"));
                finish();
            } else {
                Toast.makeText(this, ((ObjectForgotPasswordVerify) observable).getForgotPasswordVerifyResponse().getMessage(), Toast.LENGTH_SHORT).show();
            }
        } else if (observable instanceof ObjectForgotPassword) {
            progressBar.hideProgressBar();
            if (objectForgotPassword.getForgotPasswordResponse().getSuccess()) {
                Toast.makeText(this, objectForgotPassword.getForgotPasswordResponse().getMessage(), Toast.LENGTH_SHORT).show();
                resetTimer();
            } else {
                Toast.makeText(this, objectForgotPassword.getForgotPasswordResponse().getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void resetTimer() {
        mTimeLeftInMillis = START_TIME_IN_MILLIS;
        updateCountDownText();
        mCountDownTimer.cancel();
        startTimer();
    }

}