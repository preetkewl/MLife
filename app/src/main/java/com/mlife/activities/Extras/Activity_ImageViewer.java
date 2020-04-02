package com.mlife.activities.Extras;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.mlife.activities.ActivityHome;
import com.mlife.activities.ActivityNotification;
import com.mlife.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Activity_ImageViewer extends AppCompatActivity {

    @BindView(R.id.iv_ImageViewer)
    ImageView imageView;

    Intent intent;
    String Image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__image_viewer);
        ButterKnife.bind(this);
        intent = getIntent();
        Image = intent.getStringExtra("Image");
        Picasso.with(this).load(Image).placeholder(R.mipmap.placeholder).into(imageView);
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

}
