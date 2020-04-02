package com.example.focpc.mahindralifespaces.ui.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;


import com.mlife.R;

import com.example.focpc.mahindralifespaces.ui.activities.select_contact.ReferalSuccessAdapter;
import com.example.focpc.mahindralifespaces.ui.activities.select_contact.UserStatusItem;

import java.util.List;

/**
 * Created by foc pc on 06-12-2017.
 */

public class ReferSuccessDialog extends Dialog  {

    public ReferSuccessDialog(@NonNull Context context,List<UserStatusItem> userStatusArray,String programName) {
        super(context);
        getWindow().getAttributes().windowAnimations = R.style.PauseDialogAnimation;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setCancelable(true);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        setContentView(R.layout.refer_success_popup);
        Window window = getWindow();
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(window.getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);
        int newUsersCount = 0;
        for (int i = 0; i < userStatusArray.size(); i++) {
            if (userStatusArray.get(i).getUser_status() == 1) {
                newUsersCount++;
            }
        }

        TextView programNameTV = findViewById(R.id.programNameTV);
        TextView countTV = findViewById(R.id.countTV);
        programNameTV.setText(programName);
        countTV.setText(newUsersCount+" of "+ userStatusArray.size());
        RecyclerView referSuccessRecycler = (RecyclerView) findViewById(R.id.referSuccessRecycler);
        ReferalSuccessAdapter referalSuccessAdapter = new ReferalSuccessAdapter(userStatusArray);
        referSuccessRecycler.setHasFixedSize(true);
        referSuccessRecycler.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        referSuccessRecycler.setAdapter(referalSuccessAdapter);


    }







}