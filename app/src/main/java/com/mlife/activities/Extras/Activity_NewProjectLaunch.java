package com.mlife.activities.Extras;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.mlife.activities.ActivityHome;
import com.mlife.activities.ActivityNotification;
import com.mlife.activities.ActivityMyPropertyDetails;
import com.mlife.adapter.NewProjectLaunchGetterSetter;
import com.mlife.adapter.NewProjectLaunch_Adapter;
import com.mlife.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Activity_NewProjectLaunch extends AppCompatActivity {

    @BindView(R.id.rv_NewProject)
    RecyclerView recyclerView;

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

    List<NewProjectLaunchGetterSetter> dashboardList = new ArrayList<>();
    NewProjectLaunch_Adapter newProjectLaunch_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__new_project_launch);
        ButterKnife.bind(this);
        Items();

        newProjectLaunch_adapter = new NewProjectLaunch_Adapter(dashboardList, Activity_NewProjectLaunch.this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(newProjectLaunch_adapter);
        recyclerView.addOnItemTouchListener(new ActivityHome.RecyclerTouchListener(getApplicationContext(), recyclerView, new ActivityHome.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                NewProjectLaunchGetterSetter newProjectLaunchGetterSetter = dashboardList.get(position);
                startActivity(new Intent(Activity_NewProjectLaunch.this, ActivityMyPropertyDetails.class));
            }

            @Override
            public void onLongClick(View view, int position) {
            }
        }));
    }

    private void Items() {
        NewProjectLaunchGetterSetter newProjectLaunchGetterSetter = new NewProjectLaunchGetterSetter("Mahindar Lifespaces", "Mahindar Lifespaces", R.mipmap.projectluanch);
        dashboardList.add(newProjectLaunchGetterSetter);

        newProjectLaunchGetterSetter = new NewProjectLaunchGetterSetter("Mahindar Lifestyle", "About Mahindar Lifestyle", R.mipmap.projectluanchtwo);
        dashboardList.add(newProjectLaunchGetterSetter);

        newProjectLaunchGetterSetter = new NewProjectLaunchGetterSetter("Mahindar Galaxy", "About Mahindar Galaxy", R.mipmap.projectluanchthree);
        dashboardList.add(newProjectLaunchGetterSetter);

        newProjectLaunchGetterSetter = new NewProjectLaunchGetterSetter("About Mahindar Lifespaces", "About Mahindar Lifespaces", R.mipmap.projectluanch);
        dashboardList.add(newProjectLaunchGetterSetter);

        newProjectLaunchGetterSetter = new NewProjectLaunchGetterSetter("Mahindar Lifestyle", "About Mahindar Lifestyle", R.mipmap.projectluanchtwo);
        dashboardList.add(newProjectLaunchGetterSetter);
    }

    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private Activity_NewProjectLaunch.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final Activity_NewProjectLaunch.ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        }
    }

}
