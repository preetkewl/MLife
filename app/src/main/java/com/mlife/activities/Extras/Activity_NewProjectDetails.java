package com.mlife.activities.Extras;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mlife.utils.MahindraClappPreference;
import com.mlife.R;
import com.mlife.utils.DialogDropEnquiry;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Activity_NewProjectDetails extends AppCompatActivity {

    DialogDropEnquiry _dropEnquiry = new DialogDropEnquiry();
    MahindraClappPreference mahindraClappPreference;

    @BindView(R.id.iv_ProjectDetailsImage)
    ImageView iv_ProjectDetailsImage;

    @BindView(R.id.tv_ProjectLocation)
    TextView tv_ProjectLocation;

    @OnClick(R.id.btn_DropEnquiry)
    public void onDropClick() {
        _dropEnquiry.Dialogbox(Activity_NewProjectDetails.this, mahindraClappPreference.getData("Project"));
    }

    @BindView(R.id.tv_ProjectName)
    TextView tv_ProjectName;

    @BindView(R.id.tv_AboutProject)
    TextView tv_AboutProject;

// ------------------------------------

    @BindView(R.id.tv_Locations)
    TextView tv_Locations;

    @BindView(R.id.tv_Features)
    TextView tv_Features;

    @BindView(R.id.tv_Typology)
    TextView tv_Typology;

    @BindView(R.id.tv_Contact)
    TextView tv_Contact;

// -------------------------------------

    @BindView(R.id.iv_ExpandFeatures)
    ImageView iv_ExpandFeatures;

    @BindView(R.id.iv_ExpandTypology)
    ImageView iv_ExpandTypology;

    @BindView(R.id.iv_ExpandContact)
    ImageView iv_ExpandContact;

    @BindView(R.id.iv_ExpandLocation)
    ImageView iv_ExpandLocation;

// -------------------------------------

    @OnClick(R.id.cv_Features)
    public void onFeatureClick() {
        if (tv_Features.getVisibility() == View.VISIBLE) {
            tv_Features.setVisibility(View.GONE);
            iv_ExpandFeatures.setImageResource(R.mipmap.close);
        } else {
            tv_Features.setVisibility(View.VISIBLE);
            iv_ExpandFeatures.setImageResource(R.mipmap.expand);
        }
    }

    @OnClick(R.id.cv_Typology)
    public void onTypologyClick() {
        if (tv_Typology.getVisibility() == View.VISIBLE) {
            tv_Typology.setVisibility(View.GONE);
            iv_ExpandTypology.setImageResource(R.mipmap.close);
        } else {
            tv_Typology.setVisibility(View.VISIBLE);
            iv_ExpandTypology.setImageResource(R.mipmap.expand);
        }
    }

    @OnClick(R.id.cv_Location)
    public void onLocationClick() {
        if (tv_Locations.getVisibility() == View.VISIBLE) {
            tv_Locations.setVisibility(View.GONE);
            iv_ExpandLocation.setImageResource(R.mipmap.close);
        } else {
            tv_Locations.setVisibility(View.VISIBLE);
            iv_ExpandLocation.setImageResource(R.mipmap.expand);
        }
    }

    @OnClick(R.id.cv_Contact)
    public void onContactClick() {
        if (tv_Contact.getVisibility() == View.VISIBLE) {
            tv_Contact.setVisibility(View.GONE);
            iv_ExpandContact.setImageResource(R.mipmap.close);
        } else {
            tv_Contact.setVisibility(View.VISIBLE);
            iv_ExpandContact.setImageResource(R.mipmap.expand);
        }
    }

// -------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__new_project_details);
        ButterKnife.bind(this);
        mahindraClappPreference = MahindraClappPreference.getInstance(this);
    }
}