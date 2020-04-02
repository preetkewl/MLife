package com.mlife.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.mlife.utils.Constants;
import com.mlife.utils.MahindraClappPreference;
import com.mlife.utils.DialogProgressBar;
import com.mlife.web.api.Service;
import com.mlife.web.holder.DataHolder;
import com.mlife.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActivityChangeProperty extends AppCompatActivity implements Observer, DataHolder {

    PropertyAdapter adapter;
    PropertyDetailGetterSetter getterSetter;
    DialogProgressBar progressBar = new DialogProgressBar();
    MahindraClappPreference mahindraClappPreference;
    List<PropertyDetailGetterSetter> list = new ArrayList<>();
    String propertyId = "", projectId = "", project = "", bId = "", name = "", towerID = "", userType = "", latitued = "", longitude = "";

    @BindView(R.id.btn_Submit)
    Button btn_Submit;

    @BindView(R.id.rv_changeProperty)
    RecyclerView recyclerView;

    @BindView(R.id.tv_NoProperty)
    TextView tv_NoProperty;

    @BindView(R.id.srl_MyProperty)
    SwipeRefreshLayout srl_MyProperty;

    @OnClick(R.id.iv_Back_ManageDitePlan)
    public void backClick() {
        finish();
    }

    @OnClick(R.id.btn_Submit)
    public void moveOn() {
        btn_Submit.setClickable(false);

        if (propertyId.equals("")) {
            btn_Submit.setClickable(true);
            Toast.makeText(ActivityChangeProperty.this, "Please Select Atleast One Property", Toast.LENGTH_SHORT).show();
        } else {
            mahindraClappPreference.saveData("Property", propertyId);
            mahindraClappPreference.saveData("Project", projectId);
            mahindraClappPreference.saveData("ProjectName", project);
            mahindraClappPreference.saveData("bID", bId);
            mahindraClappPreference.saveData("name", name);
            mahindraClappPreference.saveData("UserType", userType);
            mahindraClappPreference.saveData("latitude", latitued);
            mahindraClappPreference.saveData("longitude", longitude);
            mahindraClappPreference.saveData("TowerId", towerID);
            startActivity(new Intent(ActivityChangeProperty.this, ActivityHome.class));
            finish();
        }
    }

    @Override
    public void onBackPressed() {

    }


    @Override
    protected void onResume() {
        super.onResume();
        FirebaseAnalytics mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle params = new Bundle();
        params.putString(FirebaseAnalytics.Param.ITEM_NAME, "Change Property Screen Android");
        mFirebaseAnalytics.setCurrentScreen(this, "Change Property Screen Android", null);
        mFirebaseAnalytics.logEvent("Change_Property_Screen_Android", params);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__change_property);
        ButterKnife.bind(this);

        progressBar.showProgressBar(ActivityChangeProperty.this);
        objectPropertyResponse.addObserver(this);
        mahindraClappPreference = MahindraClappPreference.getInstance(getApplicationContext());
        new Service().getPropertyList(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android");

        srl_MyProperty.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                progressBar.showProgressBar(ActivityChangeProperty.this);
                new Service().getPropertyList(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android");
            }
        });
    }

    @Override
    public void update(Observable o, Object arg) {

        progressBar.hideProgressBar();
        srl_MyProperty.setRefreshing(false);
        list.clear();

        if (objectPropertyResponse.getPropertyResponse().getSuccess()) {
            for (int i = 0; i < objectPropertyResponse.getPropertyResponse().getData().getProperties().size(); i++) {
                try {
                    getterSetter = new PropertyDetailGetterSetter(objectPropertyResponse.getPropertyResponse().getData().getProperties().get(i).getImage(), objectPropertyResponse.getPropertyResponse().getData().getProperties().get(i).getProject().toString(), objectPropertyResponse.getPropertyResponse().getData().getProperties().get(i).getName().toString(), objectPropertyResponse.getPropertyResponse().getData().getProperties().get(i).getPId(), objectPropertyResponse.getPropertyResponse().getData().getProperties().get(i).getBId(), objectPropertyResponse.getPropertyResponse().getData().getProperties().get(i).getStatus(), objectPropertyResponse.getPropertyResponse().getData().getProperties().get(i).getProjectId(), objectPropertyResponse.getPropertyResponse().getData().getProperties().get(i).getLat(), objectPropertyResponse.getPropertyResponse().getData().getProperties().get(i).getLong(), objectPropertyResponse.getPropertyResponse().getData().getProperties().get(i).getTowerId());
                    list.add(getterSetter);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (list.size() == 0) {
                tv_NoProperty.setVisibility(View.VISIBLE);
            }
            adapter = new PropertyAdapter(list, this);
            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(adapter);
        } else if (!objectPropertyResponse.getPropertyResponse().getSuccess()) {
            Toast.makeText(this, objectPropertyResponse.getPropertyResponse().getMessage(), Toast.LENGTH_SHORT).show();
            if (objectPropertyResponse.getPropertyResponse().getAction().toLowerCase().equals("showlogin")) {
                startActivity(new Intent(ActivityChangeProperty.this, ActivityLogin.class));
                mahindraClappPreference.clearData();
            }
        } else {
            Toast.makeText(this, "Please wait till the problem is resolved", Toast.LENGTH_SHORT).show();
        }

    }

    public class PropertyAdapter extends RecyclerView.Adapter<PropertyAdapter.MyViewHolder> {

        Context context;
        private java.util.List<PropertyDetailGetterSetter> List;
        ImageView ivChecked;

        @Override
        public PropertyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_change_property, parent, false);
            return new PropertyAdapter.MyViewHolder(itemView);
        }

        public PropertyAdapter(java.util.List<PropertyDetailGetterSetter> DataList, FragmentActivity activity) {
            this.List = DataList;
            context = activity;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView Project;
            public ImageView ProjectImage, Selected;

            public MyViewHolder(View view) {
                super(view);
                Project = (TextView) view.findViewById(R.id.tv_Project);
                ProjectImage = (ImageView) view.findViewById(R.id.iv_ProjectImage);
                Selected = (ImageView) view.findViewById(R.id.iv_selected);
            }
        }

        @Override
        public void onBindViewHolder(final PropertyAdapter.MyViewHolder holder, final int position) {
            final PropertyDetailGetterSetter detail = List.get(position);
            holder.Project.setText(detail.getName());
            Picasso.with(context).load(Constants.changeProperty + detail.getImage()).placeholder(R.mipmap.placeholderone).into(holder.ProjectImage);

            holder.ProjectImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (ivChecked == null) {
                    } else {
                        ivChecked.setVisibility(View.GONE);
                    }
                    holder.Selected.setVisibility(View.VISIBLE);
                    ivChecked = holder.Selected;
                    propertyId = detail.getPropertyId();
                    projectId = detail.getProjectId();
                    project = detail.getProject();
                    bId = detail.getbId();
                    name = detail.getName();
                    userType = detail.getStatus();
                    latitued = detail.getLat();
                    longitude = detail.getLng();
                    towerID = detail.getTowerId();
                }
            });
        }

        @Override
        public int getItemCount() {
            return List.size();
        }

    }

    public class PropertyDetailGetterSetter {
        String image, project, name, propertyId, bId, status, projectId, lat, lng, towerId;

        public PropertyDetailGetterSetter(String image, String project, String name, String propertyId, String bId, String status, String projectId, String lat, String lng, String towerId) {
            this.image = image;
            this.project = project;
            this.name = name;
            this.propertyId = propertyId;
            this.bId = bId;
            this.status = status;
            this.projectId = projectId;
            this.lat = lat;
            this.lng = lng;
            this.towerId = towerId;
        }


        public String getTowerId() {
            return towerId;
        }

        public void setTowerId(String towerId) {
            this.towerId = towerId;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLng() {
            return lng;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }

        public String getProjectId() {
            return projectId;
        }

        public void setProjectId(String projectId) {
            this.projectId = projectId;
        }

        public String getbId() {
            return bId;
        }

        public void setbId(String bId) {
            this.bId = bId;
        }

        public String getProject() {
            return project;
        }

        public void setProject(String project) {
            this.project = project;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPropertyId() {
            return propertyId;
        }

        public void setPropertyId(String propertyId) {
            this.propertyId = propertyId;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }


    }

}