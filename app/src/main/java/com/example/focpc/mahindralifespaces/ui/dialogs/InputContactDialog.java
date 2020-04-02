package com.example.focpc.mahindralifespaces.ui.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.mlife.R;

import com.example.focpc.mahindralifespaces.utils.MlsUtils;

/**
 * Created by foc pc on 08-12-2017.
 */

public class InputContactDialog extends Dialog implements View.OnClickListener {
    private InputContactDialog.DialogClickHandler dialogClickHandler;
    private EditText contNameET, contMobNumET, contEmailET;

    public InputContactDialog(@NonNull Context context, InputContactDialog.DialogClickHandler dialogClickHandler) {
        super(context);
        getWindow().getAttributes().windowAnimations = R.style.PauseDialogAnimation;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setCancelable(true);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        setContentView(R.layout.input_contact_popup);
        Window window = getWindow();
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(window.getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);

        Button submitBtn;
        contNameET = findViewById(R.id.contNameET);
        contMobNumET = findViewById(R.id.contMobNumET);
        contEmailET = findViewById(R.id.contEmailET);
        submitBtn = findViewById(R.id.submitBtn);
        this.dialogClickHandler = dialogClickHandler;
        LinearLayout commentCbParent = findViewById(R.id.commentCbParent);
        final CheckBox commentCB = findViewById(R.id.commentCB);
        submitBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.submitBtn:
                if (TextUtils.isEmpty(contNameET.getText().toString().trim())) {
                    setEtError(contNameET,"Please enter name of person");

                } else if (TextUtils.isEmpty(contMobNumET.getText().toString().trim())) {
                    setEtError(contMobNumET,"Please enter contact number of person");
                }else if (contMobNumET.getText().toString().trim().length() != 10){
                    setEtError(contMobNumET,"Please enter 10 digit mobile number");
                }  else if (TextUtils.isEmpty(contEmailET.getText().toString().trim())) {
                    setEtError(contEmailET,"Please enter email id of person");
                } else if (!MlsUtils.isValidEmail(contEmailET.getText().toString().trim())){
                    setEtError(contEmailET,"Please enter a valid email id");
                }  else {
                    dialogClickHandler.onSubMitClicked(contNameET.getText().toString().trim(),
                            contMobNumET.getText().toString().trim(),contEmailET.getText().toString().trim());
                }
                break;
        }
    }

    public interface DialogClickHandler {
        void onSubMitClicked(String name,String number,String email);
    }

    private void setEtError(EditText editText, String error) {
        if (editText != contNameET) contNameET.setError(null);
        if (editText != contMobNumET) contMobNumET.setError(null);
        if (editText != contEmailET) contEmailET.setError(null);
        editText.setError(error);
        editText.requestFocus();

    }


}
