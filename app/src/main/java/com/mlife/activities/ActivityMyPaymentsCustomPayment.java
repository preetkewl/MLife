package com.mlife.activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.billdesk.sdk.PaymentOptions;
import com.mlife.R;
import com.mlife.utils.MahindraClappPreference;
import com.mlife.utils.DialogProgressBar;
import com.mlife.web.api.Service;
import com.mlife.web.holder.DataHolder;
import com.mlife.web.holder.Response.ObjectPaymentResponse;
import com.mlife.web.model.GetPaymentDetailsDetails;
import com.mlife.payment.SampleCallBack;

import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActivityMyPaymentsCustomPayment extends AppCompatActivity implements DataHolder, Observer {

    @BindView(R.id.cb_customAmount)
    CheckBox cb_customAmount;

    @BindView(R.id.ed_amount)
    EditText ed_amount;

    @BindView(R.id.tv_InvoiceNumber)
    TextView tv_InvoiceNumber;

    @BindView(R.id.tv_Date)
    TextView tv_Date;

    @BindView(R.id.tv_Amount)
    TextView tv_Amount;

    @BindView(R.id.tv_DueDate)
    TextView tv_DueDate;

    @BindView(R.id.tv_Description)
    TextView tv_Description;

    DialogProgressBar progressBar = new DialogProgressBar();

    GetPaymentDetailsDetails detail;

    SampleCallBack callbackObj;

    MahindraClappPreference mahindraClappPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__custom_payment);
        ButterKnife.bind(this);


        objectPaymentResponse.addObserver(this);

        callbackObj = new SampleCallBack();

        ed_amount.setFocusable(false);
        ed_amount.setFocusableInTouchMode(false);
        ed_amount.setClickable(false);

        mahindraClappPreference = MahindraClappPreference.getInstance(getApplicationContext());

        detail = (GetPaymentDetailsDetails) getIntent().getSerializableExtra("detail");



        ed_amount.setText(detail.getAmount());
        tv_InvoiceNumber.setText("Invoice No. "+detail.getRef());
        tv_Amount.setText("Amount: INR  "+detail.getAmount());
        tv_DueDate.setText("Due Date: "+ detail.getDate());


        tv_Description.setText("Description: "+detail.getDescription());



        cb_customAmount.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                if(isChecked){

                    ed_amount.setFocusable(true);
                    ed_amount.setFocusableInTouchMode(true);
                    ed_amount.setClickable(true);

                }else{
                    ed_amount.setText(detail.getAmount());
                    ed_amount.setFocusable(false);
                    ed_amount.setFocusableInTouchMode(false);
                    ed_amount.setClickable(false);
                }


            }
        });



    }

    @OnClick(R.id.btn_Pay)
    public void payAmountClick(){

        if(!ed_amount.getText().toString().equals("")  &&  Double.parseDouble(ed_amount.getText().toString()) > 0 && Double.parseDouble(ed_amount.getText().toString()) <= Double.parseDouble(detail.getAmount())) {
            progressBar.showProgressBar(this);
            new Service().insertPaymentDetail(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"),
                    mahindraClappPreference.getData("DeviceToken"), "Android", mahindraClappPreference.getData("Property"),
                    mahindraClappPreference.getData("bID"),
                    mahindraClappPreference.getData("TowerId"), detail.getPaymentId(), ed_amount.getText().toString().trim());
        }else{
            Toast.makeText(this, "Please enter valid amount", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.iv_Back_ManageDitePlan)
    public void backClick() {
        finish();
    }

    @OnClick(R.id.iv_Notification)
    public void notificationClick() {
        startActivity(new Intent(getApplicationContext(), ActivityNotification.class));
    }

    @OnClick(R.id.iv_MahindraLogo)
    public void logoClick() {
        startActivity(new Intent(getApplicationContext(), ActivityHome.class));
    }

    @Override
    public void update(Observable observable, Object o) {

        if (observable instanceof ObjectPaymentResponse) {
            progressBar.hideProgressBar();

                if (objectPaymentResponse.getmPaymentResponse().getSuccess()) {
                    try {
                        Intent intent = new Intent(this, PaymentOptions.class);
                        intent.putExtra("msg", objectPaymentResponse.getmPaymentResponse().getData()); // pg_msg
                        intent.putExtra("txtpaycategory", "NB");
                        intent.putExtra("user-email", mahindraClappPreference.getData("Email"));
                        intent.putExtra("user-mobile", mahindraClappPreference.getData("Mobile"));
                        intent.putExtra("orientation", Configuration.ORIENTATION_PORTRAIT);
                        intent.putExtra("callback", callbackObj);

//                        Log.e("PG MSG",objectPaymentResponse.getmPaymentResponse().getData());// pg_msg
                        startActivity(intent);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else {
                    Toast.makeText(this, objectPaymentResponse.getmPaymentResponse().getMessage(), Toast.LENGTH_SHORT).show();
                    if (!objectPaymentResponse.getmPaymentResponse().getAction().isEmpty()) {
                        startActivity(new Intent(this, ActivityLoginWithOTP.class));
                    }
                }
                // check alternate solution of this
                objectPaymentResponse.getmPaymentResponse().setSuccess(false);
        }

    }
}