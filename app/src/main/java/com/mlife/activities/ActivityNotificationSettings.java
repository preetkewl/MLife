package com.mlife.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.mlife.R;
import com.mlife.utils.MahindraClappPreference;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActivityNotificationSettings extends AppCompatActivity {


    Boolean isNotification, isSound;
    MahindraClappPreference mahindraClappPreference;

    @BindView(R.id.sw_Notification)
    Switch sw_Notification;

    @BindView(R.id.sw_Sound)
    Switch sw_Sound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__edit_profile);
        ButterKnife.bind(this);
        mahindraClappPreference = MahindraClappPreference.getInstance(getApplicationContext());
        sw_Notification.setChecked(mahindraClappPreference.getNotification());
        sw_Sound.setChecked(mahindraClappPreference.getSound());

        sw_Notification.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                sw_Sound.setChecked(isChecked);
                mahindraClappPreference.setSound(isChecked);
                mahindraClappPreference.setNotification(isChecked);

                if (!isChecked) {
                    sw_Sound.setClickable(false);
                } else {
                    sw_Sound.setClickable(true);
                }
            }
        });

        sw_Sound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mahindraClappPreference.setSound(isChecked);
            }
        });
    }

    @OnClick(R.id.iv_Back_ManageDitePlan)
    public void backClick() {
        finish();
    }

    @OnClick(R.id.iv_MahindraLogo)
    public void logoClick() {
        startActivity(new Intent(getApplicationContext(), ActivityHome.class));
    }

}