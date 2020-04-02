package com.mlife.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mlife.activities.Extras.Activity_ThankYou;
import com.mlife.web.api.Service;
import com.mlife.web.holder.DataHolder;
import com.mlife.web.holder.Response.ObjectAddCallBack;
import com.mlife.R;

import java.util.Observable;
import java.util.Observer;

import static com.mlife.activities.ActivityHome.userType;

/**
 * Created by milagro on 9/15/2017.
 */

public class DialogRequestCallback implements DataHolder, Observer {

    Activity activity;
    Validations validations;
    DialogProgressBar progressBar;
    MahindraClappPreference mahindraClappPreference;
    String propType = "1";

    EditText et_NameCallBack, et_DescriptionCallBack, et_LastNameCallBack;
    EditText et_EmailCallBack;
    EditText et_MobileCallBack;
    Button btn_SubmitCallBack;

    public void Dialogbox(final Activity activity) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_callback);
        dialog.setCanceledOnTouchOutside(true);
        progressBar = new DialogProgressBar();
        this.activity = activity;

        et_NameCallBack = (EditText) dialog.findViewById(R.id.et_NameCallBack);
        et_LastNameCallBack = (EditText) dialog.findViewById(R.id.et_LastNameCallBack);
        et_EmailCallBack = (EditText) dialog.findViewById(R.id.et_EmailCallBack);
        et_MobileCallBack = (EditText) dialog.findViewById(R.id.et_MobileCallBack);
        et_DescriptionCallBack = (EditText) dialog.findViewById(R.id.et_DescriptionCallBack);
        btn_SubmitCallBack = (Button) dialog.findViewById(R.id.btn_SubmitCallBack);

        btn_SubmitCallBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                et_NameCallBack.setError(null);
                et_EmailCallBack.setError(null);
                et_MobileCallBack.setError(null);

                if (!validations.nameValidation(et_NameCallBack.getText().toString().trim())) {
                    et_NameCallBack.setError("Enter valid name");
                    et_NameCallBack.requestFocus();
                }
                if (!validations.nameValidation(et_LastNameCallBack.getText().toString().trim())) {
                    et_LastNameCallBack.setError("Enter valid last name");
                    et_LastNameCallBack.requestFocus();
                } else if (!validations.emailValidation(et_EmailCallBack.getText().toString().trim())) {
                    et_EmailCallBack.setError("Enter valid email");
                    et_EmailCallBack.requestFocus();

                } else if (!validations.indiaNumber(et_MobileCallBack.getText().toString().trim())) {
                    et_MobileCallBack.setError("Enter valid number");
                    et_MobileCallBack.requestFocus();

                } else {
                    progressBar.showProgressBar(activity);
                    btn_SubmitCallBack.setClickable(false);
                    if (userType.equals(Constants.postSales)) {
                        propType = "1";
                    } else {
                        propType = "2";
                    }
                    new Service().addCallBackReq(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android", mahindraClappPreference.getData("bID"), et_NameCallBack.getText().toString().trim() + " " + et_LastNameCallBack.getText().toString().trim(), et_EmailCallBack.getText().toString(), et_MobileCallBack.getText().toString(), "0", mahindraClappPreference.getData("Project"), propType, et_DescriptionCallBack.getText().toString().trim());
                }
            }
        });
        mahindraClappPreference = MahindraClappPreference.getInstance(activity);
        validations = new Validations();
        objectAddCallBack.addObserver(this);
        dialog.show();
    }

    @Override
    public void update(Observable o, Object arg) {
        progressBar.hideProgressBar();
        if (o instanceof ObjectAddCallBack) {
            if (objectAddCallBack.getAddCallBackResponse().getSuccess()) {
                Log.e("Check ##",objectAddCallBack.getAddCallBackResponse()+"");
                btn_SubmitCallBack.setClickable(true);
                activity.startActivity(new Intent(activity, Activity_ThankYou.class).putExtra("Title", "Request Callback").putExtra("Heading", "Thank You").putExtra("Detail", "Your request has been received \n our representative will contact you soon."));
                activity.finish();
            } else {
                Toast.makeText(activity, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
