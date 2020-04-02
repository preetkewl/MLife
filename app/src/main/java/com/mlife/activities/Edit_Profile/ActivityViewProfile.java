package com.mlife.activities.Edit_Profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.mikhaellopez.circularimageview.CircularImageView;
import com.mlife.activities.ActivityHome;
import com.mlife.activities.ActivityNotification;
import com.mlife.R;
import com.mlife.utils.Constants;
import com.mlife.utils.MahindraClappPreference;
import com.mlife.utils.DialogProgressBar;
import com.mlife.web.api.Service;
import com.mlife.web.holder.DataHolder;
import com.mlife.web.holder.Response.ObjectViewProfileDetails;
import com.squareup.picasso.Picasso;

import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActivityViewProfile extends AppCompatActivity implements Observer, DataHolder {

    @BindView(R.id.iv_UserImage)
    CircularImageView iv_UserImage;

    @BindView(R.id.tv_Name)
    TextView tv_Name;

    @BindView(R.id.tv_Occupation)
    TextView tv_Occupation;

    @BindView(R.id.tv_EducationOne)
    TextView tv_EducationOne;

    @BindView(R.id.tv_EducationTwo)
    TextView tv_EducationTwo;

    @BindView(R.id.tv_EducationThree)
    TextView tv_EducationThree;

    @BindView(R.id.tv_UserContactNumber)
    TextView tv_UserContactNumber;

    @BindView(R.id.tv_UserEmailId)
    TextView tv_UserEmailId;

    @BindView(R.id.tv_UserUnitId)
    TextView tv_UserUnitId;

    Intent intent = new Intent();
    DialogProgressBar progressBar = new DialogProgressBar();
    MahindraClappPreference mahindraClappPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__view_profile);
        ButterKnife.bind(this);
        iv_UserImage.bringToFront();
        intent = getIntent();
        mahindraClappPreference = MahindraClappPreference.getInstance(this);
        progressBar.showProgressBar(this);
        new Service().viewProfileDetails(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android", intent.getStringExtra("profileId"));
        objectViewProfileDetails.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof ObjectViewProfileDetails) {
            if (objectViewProfileDetails.getViewProfileDetailsResponse().getSuccess()) {

                Picasso.with(getBaseContext()).load(Constants.baseUrl+ objectViewProfileDetails.getViewProfileDetailsResponse().getData().get(0).getImagePath()).placeholder(R.mipmap.comment_user).into(iv_UserImage);
                tv_Name.setText(objectViewProfileDetails.getViewProfileDetailsResponse().getData().get(0).getName());
                if (!objectViewProfileDetails.getViewProfileDetailsResponse().getData().get(0).getDesignation().equals(""))
                tv_Occupation.setText(objectViewProfileDetails.getViewProfileDetailsResponse().getData().get(0).getDesignation() + " at " + objectViewProfileDetails.getViewProfileDetailsResponse().getData().get(0).getCompanyName());
                tv_UserContactNumber.setText("Contact Number: " + objectViewProfileDetails.getViewProfileDetailsResponse().getData().get(0).getMobile());
                tv_UserEmailId.setText("Email: " + objectViewProfileDetails.getViewProfileDetailsResponse().getData().get(0).getEmail());
                tv_UserUnitId.setText("Unit: " + objectViewProfileDetails.getViewProfileDetailsResponse().getData().get(0).getEmail());

                switch (objectViewProfileDetails.getViewProfileDetailsResponse().getData().get(0).getEducation().size()) {
                    case 0:
                        break;
                    case 1:
                        tv_EducationOne.setVisibility(View.VISIBLE);
                        tv_EducationOne.setText(objectViewProfileDetails.getViewProfileDetailsResponse().getData().get(0).getEducation().get(0).getDegree()+" from " + objectViewProfileDetails.getViewProfileDetailsResponse().getData().get(0).getEducation().get(0).getUniversity());
                        break;
                    case 2:
                        tv_EducationOne.setVisibility(View.VISIBLE);
                        tv_EducationOne.setText(objectViewProfileDetails.getViewProfileDetailsResponse().getData().get(0).getEducation().get(0).getDegree()+" from " + objectViewProfileDetails.getViewProfileDetailsResponse().getData().get(0).getEducation().get(0).getUniversity());
                        tv_EducationTwo.setVisibility(View.VISIBLE);
                        tv_EducationTwo.setText(objectViewProfileDetails.getViewProfileDetailsResponse().getData().get(0).getEducation().get(1).getDegree()+" from " + objectViewProfileDetails.getViewProfileDetailsResponse().getData().get(0).getEducation().get(1).getUniversity());
                        break;
                    case 3:
                        tv_EducationOne.setVisibility(View.VISIBLE);
                        tv_EducationOne.setText(objectViewProfileDetails.getViewProfileDetailsResponse().getData().get(0).getEducation().get(0).getDegree()+" from " + objectViewProfileDetails.getViewProfileDetailsResponse().getData().get(0).getEducation().get(0).getUniversity());
                        tv_EducationTwo.setVisibility(View.VISIBLE);
                        tv_EducationTwo.setText(objectViewProfileDetails.getViewProfileDetailsResponse().getData().get(0).getEducation().get(1).getDegree()+" from " + objectViewProfileDetails.getViewProfileDetailsResponse().getData().get(0).getEducation().get(1).getUniversity());
                        tv_EducationThree.setVisibility(View.VISIBLE);
                        tv_EducationThree.setText(objectViewProfileDetails.getViewProfileDetailsResponse().getData().get(0).getEducation().get(2).getDegree()+" from " + objectViewProfileDetails.getViewProfileDetailsResponse().getData().get(0).getEducation().get(2).getUniversity());
                        break;
                }

            } else {
                if (objectViewProfileDetails.getViewProfileDetailsResponse().getAction().toLowerCase().equals("showlogin")) {

                }
            }
            progressBar.hideProgressBar();
        }
    }


    @OnClick(R.id.iv_Back_ManageDitePlan)
    public void backClick() {
        finish();
    }

    @OnClick(R.id.iv_Notification)
    public void notificationClick() {
        startActivity(new Intent(getApplicationContext(), ActivityNotification.class));
        finish();
    }

    @OnClick(R.id.iv_MahindraLogo)
    public void logoClick() {
        startActivity(new Intent(getApplicationContext(), ActivityHome.class));
        finish();
    }

}