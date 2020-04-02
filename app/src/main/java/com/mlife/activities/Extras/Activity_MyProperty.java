package com.mlife.activities.Extras;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mlife.activities.ActivityHome;
import com.mlife.activities.ActivityNotification;
import com.mlife.adapter.NewProjectLaunchGetterSetter;
import com.mlife.adapter.NewProjectLaunch_Adapter;
import com.mlife.R;
import com.mlife.utils.DialogProgressBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Activity_MyProperty extends AppCompatActivity {

    List<NewProjectLaunchGetterSetter> list = new ArrayList<>();
    NewProjectLaunch_Adapter adapter;
    DialogProgressBar progressBar = new DialogProgressBar();


    @BindView(R.id.rv_MyProperty)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__my_property);
        ButterKnife.bind(this);
        prepairData();
        adapter = new NewProjectLaunch_Adapter(list, Activity_MyProperty.this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(new ActivityHome.RecyclerTouchListener(getApplicationContext(), recyclerView, new ActivityHome.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                NewProjectLaunchGetterSetter getterSetter = list.get(position);
                startActivity(new Intent(Activity_MyProperty.this, Activity_MyPropertyDetails.class));
            }

            @Override
            public void onLongClick(View view, int position) {
            }
        }));

    }

    public void prepairData() {
        NewProjectLaunchGetterSetter getterSetter = new NewProjectLaunchGetterSetter("Mahindar Lifespaces", "Mahindar Lifespaces", R.mipmap.projectluanch);
        list.add(getterSetter);

        getterSetter = new NewProjectLaunchGetterSetter("Mahindar Lifestyle", "About Mahindar Lifestyle", R.mipmap.projectluanchtwo);
        list.add(getterSetter);

        getterSetter = new NewProjectLaunchGetterSetter("Mahindar Galaxy", "About Mahindar Galaxy", R.mipmap.projectluanchthree);
        list.add(getterSetter);

        getterSetter = new NewProjectLaunchGetterSetter("About Mahindar Lifespaces", "About Mahindar Lifespaces", R.mipmap.projectluanch);
        list.add(getterSetter);

        getterSetter = new NewProjectLaunchGetterSetter("Mahindar Lifestyle", "About Mahindar Lifestyle", R.mipmap.projectluanchtwo);
        list.add(getterSetter);
    }

    @OnClick(R.id.iv_Back_ManageDitePlan)
    public void backClick(){
        finish();
    }

    @OnClick(R.id.iv_Notification)
    public void notificationClick(){
        startActivity(new Intent(getApplicationContext(), ActivityNotification.class));
    }

    @OnClick(R.id.iv_MahindraLogo)
    public void logoClick(){
        startActivity(new Intent(getApplicationContext(), ActivityHome.class));
    }

}
