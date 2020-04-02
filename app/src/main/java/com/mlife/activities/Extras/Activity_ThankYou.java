package com.mlife.activities.Extras;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.mlife.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Activity_ThankYou extends AppCompatActivity {

    Intent intent;
    String check = "default";

    @BindView(R.id.tv_Title)
    TextView tvTitle;

    @BindView(R.id.iv_DisplayImage)
    ImageView ivDisplayImage;

    @BindView(R.id.tv_TextHeading)
    TextView tvHeading;

    @BindView(R.id.tv_TextDetails)
    TextView tvDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__thank_you);
        ButterKnife.bind(this);
        intent = getIntent();
        check = intent.getStringExtra("callBack");
        tvTitle.setText(intent.getStringExtra("Title"));
        tvHeading.setText(intent.getStringExtra("Heading"));
        tvDetails.setText(intent.getStringExtra("Detail"));
        ivDisplayImage.setImageResource(intent.getIntExtra("Image", R.mipmap.thankyouiconclubhouse));
    }

    @Override
    public void onBackPressed() {
        try {
            if (check.equals("SiteVisit")) {
                finish();
            } else if (check.equals("Clubhouse")) {
                finish();
            } else if (check.equals("ClassifiedSell")) {
                finish();
            } else if (check.equals("ClassifiedRent")) {
                finish();
            } else if (check.equals("MyRequest")) {
                finish();
            } else if (check.equals("Vas")) {
                finish();
            }else if (check.equals("ForgotPassword")) {
                finish();
            } else {
                finish();
            }
        } catch (Exception ex) {
            finish();
        }
    }

    @OnClick(R.id.iv_Cross)
    public void cross() {
        try {
            if (check.equals("SiteVisit")) {
                finish();
            } else if (check.equals("Clubhouse")) {
                finish();
            } else if (check.equals("ClassifiedSell")) {
                finish();
            } else if (check.equals("ClassifiedRent")) {
                finish();
            } else if (check.equals("MyRequest")) {
                finish();
            } else if (check.equals("Vas")) {
                finish();
            }else if (check.equals("ForgotPassword")) {
                finish();
            } else {
                finish();
            }
        } catch (Exception ex) {
            finish();
        }
    }
}