package com.mlife.activities.Extras;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mlife.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class Activity_TermsAndConditions extends AppCompatActivity {

    @OnClick(R.id.iv_Back_ManageDitePlan)
    public void finishActivity(){
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__terms_and_conditions);
        ButterKnife.bind(this);
    }
}
