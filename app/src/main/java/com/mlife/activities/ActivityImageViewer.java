package com.mlife.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.mlife.adapter.ProjectImageGetterSetter;
import com.mlife.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActivityImageViewer extends AppCompatActivity {

    @BindView(R.id.iv_Construction_update_image_viewer)
    ImageView iv_Construction_update_image_viewer;

    ArrayList<String> myList;
    ArrayList<ProjectImageGetterSetter> myData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__construction__update__image__viewer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        final Bitmap decodedByte = BitmapFactory.decodeByteArray(getIntent().getByteArrayExtra("Data"), 0, getIntent().getByteArrayExtra("Data").length);
        iv_Construction_update_image_viewer.setImageBitmap(decodedByte);
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