package com.mlife.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.mlife.R;
import com.mlife.utils.DialogProgressBar;
import com.mlife.utils.Validations;
import com.mlife.web.api.Service;
import com.mlife.web.holder.DataHolder;
import com.mlife.web.holder.Response.ObjectForgotPassword;

import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by milagro on 1/30/2018.
 */

public class ActivityForgotPassword extends AppCompatActivity implements DataHolder, Observer {

    String email, deviceToken;
    DialogProgressBar progressBar = new DialogProgressBar();
    Validations validations = new Validations();

    @BindView(R.id.et_Email)
    EditText et_Email;

    @BindView(R.id.btn_Send)
    Button btn_Send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__reset_password);
        ButterKnife.bind(this);
        objectForgotPassword.addObserver(this);
        deviceToken = FirebaseInstanceId.getInstance().getToken();
    }

    @OnClick(R.id.btn_Send)
    public void omClick() {
        if (!validations.emailAndPhoneValidation(et_Email.getText().toString())) {
            et_Email.requestFocus();
            et_Email.setError("Enter Valid E-mail Id");
        } else {
            btn_Send.setClickable(false);
            progressBar.showProgressBar(ActivityForgotPassword.this);
            new Service().forgotPassword(et_Email.getText().toString().trim(), deviceToken, "Android");
        }
    }

    @OnClick(R.id.iv_Back_ManageDitePlan)
    public void crossClick() {
        finish();
    }

    @Override
    public void update(Observable observable, Object o) {
        if (observable instanceof ObjectForgotPassword) {
            progressBar.hideProgressBar();
            btn_Send.setClickable(true);
            if (objectForgotPassword.getForgotPasswordResponse().getSuccess()) {
                startActivity(new Intent(ActivityForgotPassword.this, ActivityForgotPasswordOTP.class).putExtra("Email",et_Email.getText().toString().trim()).putExtra("Token",deviceToken));
                finish();
            } else {
                Toast.makeText(this, objectForgotPassword.getForgotPasswordResponse().getMessage(), Toast.LENGTH_SHORT).show();
            }
            observable.deleteObservers();
        }
    }
}