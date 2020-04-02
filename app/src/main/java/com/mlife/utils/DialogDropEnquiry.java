package com.mlife.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mlife.activities.Extras.Activity_ThankYou;
import com.mlife.web.api.Service;
import com.mlife.web.holder.DataHolder;
import com.mlife.R;
import com.mlife.web.holder.Response.ObjectSendNewEnquiry;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by milagro on 8/23/2017.
 */

public class DialogDropEnquiry implements DataHolder, Observer {

    Activity activity;
    EditText et_FirstNameDropEnquiry, et_LastNameDropEnquiry, et_EmailDropEnquiry, et_MobileDropEnquiry, et_DescriptionDropEnquiry;
    Button btn_Submit_Forgot;
    Validations validations;
    DialogProgressBar progressBar = new DialogProgressBar();
    MahindraClappPreference mahindraClappPreference;

    public void Dialogbox(final Activity activity, final String id) {

        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_drop_an_enquiry);
        dialog.setCanceledOnTouchOutside(true);
        mahindraClappPreference = MahindraClappPreference.getInstance(activity);
        this.activity = activity;
        validations = new Validations();
        et_FirstNameDropEnquiry = (EditText) dialog.findViewById(R.id.et_FirstNameDropEnquiry);
        et_LastNameDropEnquiry = (EditText) dialog.findViewById(R.id.et_LastNameDropEnquiry);
        et_EmailDropEnquiry = (EditText) dialog.findViewById(R.id.et_EmailDropEnquiry);
        et_MobileDropEnquiry = (EditText) dialog.findViewById(R.id.et_MobileDropEnquiry);
        et_DescriptionDropEnquiry = (EditText) dialog.findViewById(R.id.et_DescriptionCallBack);
        btn_Submit_Forgot = (Button) dialog.findViewById(R.id.btn_Submit_Forgot);
        objectSendNewEnquiry.addObserver(this);

        btn_Submit_Forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                et_FirstNameDropEnquiry.setError(null);
                et_LastNameDropEnquiry.setError(null);
                et_EmailDropEnquiry.setError(null);
                et_MobileDropEnquiry.setError(null);

                if (!validations.nameValidation(et_FirstNameDropEnquiry.getText().toString().trim())) {
                    et_FirstNameDropEnquiry.setError("Enter Valid First Name");
                    et_FirstNameDropEnquiry.requestFocus();
                } else if (!validations.nameValidation(et_LastNameDropEnquiry.getText().toString().trim())) {
                    et_LastNameDropEnquiry.setError("Enter Valid Last Name");
                    et_LastNameDropEnquiry.requestFocus();
                } else if (!validations.emailValidation(et_EmailDropEnquiry.getText().toString().trim())) {
                    et_EmailDropEnquiry.setError("Enter Valid Email");
                    et_EmailDropEnquiry.requestFocus();
                } else if (!validations.indiaNumber(et_MobileDropEnquiry.getText().toString().trim())) {
                    et_MobileDropEnquiry.setError("Enter Valid Number");
                    et_MobileDropEnquiry.requestFocus();
                } else {
                    btn_Submit_Forgot.setClickable(false);
                    progressBar.showProgressBar(activity);
                    new Service().sendNewEnquiry(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android", et_FirstNameDropEnquiry.getText().toString().trim(), et_LastNameDropEnquiry.getText().toString().trim(), et_EmailDropEnquiry.getText().toString(), et_MobileDropEnquiry.getText().toString(), id, et_DescriptionDropEnquiry.getText().toString());
                }
            }
        });
        dialog.show();
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof ObjectSendNewEnquiry) {
            progressBar.hideProgressBar();
            btn_Submit_Forgot.setClickable(true);
            if (objectSendNewEnquiry.getSendNewEnquiryResponse().getSuccess()) {
                activity.startActivity(new Intent(activity, Activity_ThankYou.class).putExtra("Title", "Drop an Enquiry").putExtra("Heading", "Thank You").putExtra("Detail", "Your request has been received \n our representative will contact you soon."));
            } else {
                Toast.makeText(activity, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        }
    }
}