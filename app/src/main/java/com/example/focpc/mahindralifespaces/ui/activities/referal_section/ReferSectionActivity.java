package com.example.focpc.mahindralifespaces.ui.activities.referal_section;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mlife.R;

import com.example.focpc.mahindralifespaces.ui.activities.promote_us.PromoteUsActivity;
import com.example.focpc.mahindralifespaces.ui.activities.refer_now.ReferNowActivity;
import com.example.focpc.mahindralifespaces.ui.activities.reward_wallet.RewardWalletActivity;
import com.example.focpc.mahindralifespaces.ui.activities.splash.SplashActivity;
import com.example.focpc.mahindralifespaces.utils.BaseActivity;
import com.example.focpc.mahindralifespaces.utils.MlsUtils;
import com.mlife.utils.MahindraClappPreference;

public class ReferSectionActivity extends BaseActivity implements View.OnClickListener, ReferSectionView {
    private CardView referNowCard, disclaimerLayout, promoteUsCard, rewardWalletCard;
    private ConstraintLayout referSectonCL;
    private Button agreeBtn;
    private ReferSectionPresenter referSectionPresenter;
    private RecyclerView discRV;
    private DisclaimerRecyclerAdapter disclaimerRecyclerAdapter;
    private TextView termsTV,privacyTV;
    MahindraClappPreference mahindraClappPreference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refer_section);

        mahindraClappPreference = MahindraClappPreference.getInstance(getApplicationContext());

        referNowCard = findViewById(R.id.referNowCard);
        referSectonCL = findViewById(R.id.referSectonCL);
        agreeBtn = findViewById(R.id.agreeBtn);
        disclaimerLayout = findViewById(R.id.disclaimerLayout);
        promoteUsCard = findViewById(R.id.promoteUsCard);
        rewardWalletCard = findViewById(R.id.rewardWalletCard);
        discRV = findViewById(R.id.discRV);
        termsTV = findViewById(R.id.termsTV);
        privacyTV = findViewById(R.id.privacyTV);
        referNowCard.setOnClickListener(this);
        agreeBtn.setOnClickListener(this);
        promoteUsCard.setOnClickListener(this);
        rewardWalletCard.setOnClickListener(this);
        termsTV.setOnClickListener(this);
        privacyTV.setOnClickListener(this);
        referSectionPresenter = new ReferSectionPresenterImple(this);

        if (!TextUtils.isEmpty(MlsUtils.getApiToken(this))) {
            disclaimerLayout.setVisibility(View.GONE);
            referSectonCL.setVisibility(View.VISIBLE);
            startActivity(new Intent(this, SplashActivity.class));
            overridePendingTransition(R.anim.push_up_in, R.anim.fade_out);
        }

        initRV();


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.agreeBtn:
                if (TextUtils.isEmpty(MlsUtils.getApiToken(this))) {
                    if (isNetworkAvailable()) {
                        //TODO change below three lines
                        String mob = mahindraClappPreference.getpMobile();
                        String name = mahindraClappPreference.getpName();
                        String email = mahindraClappPreference.getpEmail();
                        referSectionPresenter.addnewUser(mob, name, email);
                    } else {
                        MlsUtils.showToast(this, getString(R.string.network_unavailable), false);
                    }
                } else {
                    disclaimerLayout.setVisibility(View.GONE);
                    referSectonCL.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.promoteUsCard:
                startActivity(new Intent(this, PromoteUsActivity.class));
                break;
            case R.id.rewardWalletCard:
                startActivity(new Intent(this, RewardWalletActivity.class));
                break;
            case R.id.referNowCard:
                startActivity(new Intent(this, ReferNowActivity.class));
                break;
            case R.id.termsTV:
                MlsUtils.showInWebView("https://mahindra.loyalie.in/terms_conditions.html",this);
                break;
            case R.id.privacyTV:
                MlsUtils.showInWebView("https://mahindra.loyalie.in/privacy_policy.html",this);
                break;

        }

    }


    @Override
    public void onUserAdded(UserItem userItem) {
        disclaimerLayout.setVisibility(View.GONE);
        referSectonCL.setVisibility(View.VISIBLE);
        startActivity(new Intent(this, SplashActivity.class));
        overridePendingTransition(R.anim.push_up_in, R.anim.fade_out);
        MlsUtils.setUserData(userItem, this);
    }

    @Override
    public void onUserAdditionFailed(String message) {
        MlsUtils.showToast(this, message, false);
    }

    private void initRV(){

        String[] planets = getResources().getStringArray(R.array.disc_array);
        disclaimerRecyclerAdapter = new DisclaimerRecyclerAdapter(planets,this);
        discRV.setLayoutManager(new LinearLayoutManager(this));
        discRV.setHasFixedSize(true);
        discRV.setAdapter(disclaimerRecyclerAdapter);

    }

}
