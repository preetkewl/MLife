package com.mlife.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.mlife.R;
import com.mlife.utils.AppUtils;
import com.mlife.utils.Constants;
import com.mlife.utils.DialogProgressBar;
import com.mlife.utils.MahindraClappPreference;
import com.mlife.utils.Validations;
import com.mlife.web.api.Service;
import com.mlife.web.holder.DataHolder;
import com.mlife.web.holder.Response.ObjectLoginOTPResponse;
import com.mlife.web.holder.Response.ObjectLoginResponse;

import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActivityLoginWithOTP extends AppCompatActivity implements Observer, DataHolder {

    private Service service = new Service();
    private Validations validations = new Validations();
    private static String deviceToken = "";
    private MahindraClappPreference mahindraClappPreference;
    private DialogProgressBar progressBar = new DialogProgressBar();

    @BindView(R.id.etEmail)
    EditText etEmail;

    @BindView(R.id.cbRememberUsername)
    CheckBox cb_RememberUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_with_otp);
        ButterKnife.bind(this);
        deviceToken = FirebaseInstanceId.getInstance().getToken();
        oObjectLoginOTPResponse.addObserver(this);
        mahindraClappPreference = MahindraClappPreference.getInstance(getApplicationContext());

        if (mahindraClappPreference.getRemember()) {
            cb_RememberUsername.setChecked(true);
            etEmail.setText(mahindraClappPreference.getUsername());
        }

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        oObjectLoginOTPResponse.deleteObservers();
    }


    @OnClick(R.id.btnLogin)
    public void onLoginClick() {

        progressBar.showProgressBar(ActivityLoginWithOTP.this);

        if (!validations.emailAndPhoneValidation(etEmail.getText().toString())) {
            etEmail.setError("Enter Valid Email Address");
            return;
        }

        service.loginOTP(etEmail.getText().toString(), deviceToken, "Android");

    }

    @Override
    public void update(Observable o, Object arg) {

        progressBar.hideProgressBar();

        if (o instanceof ObjectLoginOTPResponse) {

            if (cb_RememberUsername.isChecked()) {
                mahindraClappPreference.setUsername(etEmail.getText().toString().trim());
                mahindraClappPreference.setRemember(true);
            } else {
                mahindraClappPreference.setRemember(false);
            }

            if (oObjectLoginOTPResponse.getLoginResponse().getSuccess()) {

                startActivity(new Intent(this, ActivityLoginOTP.class)
                        .putExtra("data", oObjectLoginOTPResponse.getLoginResponse().getData())
                        .putExtra("email", etEmail.getText().toString()));

                finishAffinity();
            } else {
                Toast.makeText(ActivityLoginWithOTP.this, "Something went wrong, Try again later", Toast.LENGTH_SHORT).show();
            }
        }
    }
}