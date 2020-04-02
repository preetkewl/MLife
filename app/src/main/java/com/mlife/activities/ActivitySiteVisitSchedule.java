package com.mlife.activities;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.mlife.activities.Extras.Activity_ThankYou;
import com.mlife.utils.MahindraClappPreference;
import com.mlife.utils.DialogProgressBar;
import com.mlife.web.api.Service;
import com.mlife.web.holder.DataHolder;
import com.mlife.web.holder.Response.ObjectGetTimeSlot;
import com.mlife.web.holder.Response.ObjectSetSiteVisitDate;
import com.mlife.R;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActivitySiteVisitSchedule extends AppCompatActivity implements Observer, DataHolder {

    String timeSlot = "";
    String str_date = "";
    Time_Adapter time_adapter;
    TimeGetterSetter timeGetterSetter;
    int year, month, day, mYear, mMonth, mDay;
    DialogProgressBar progressBar = new DialogProgressBar();
    List<TimeGetterSetter> list = new ArrayList<>();
    List<String> propertiesList = new ArrayList<>();
    MahindraClappPreference mahindraClappPreference;

    @BindView(R.id.et_Date)
    EditText editText;

    @BindView(R.id.rv_TimeSlot)
    RecyclerView recyclerView;

//    @BindView(R.id.sp_MyProperty)
//    Spinner sp_Property;

    @BindView(R.id.btn_ScheduleVisit)
    Button btn_ScheduleVisit;


    @Override
    protected void onResume() {
        super.onResume();
        FirebaseAnalytics mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle params = new Bundle();
        params.putString(FirebaseAnalytics.Param.ITEM_NAME, "Schedule Site Visit Screen Android");
        mFirebaseAnalytics.setCurrentScreen(this, "Schedule Site Visit Screen Android", null);
        mFirebaseAnalytics.logEvent("Schedule_Site_Visit_Screen_Android", params);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__schedule_site_visit);
//        progressBar.showProgressBar(Activity_ScheduleSiteVisit.this);
        ButterKnife.bind(this);
//
//        AnalyticsApplication application = (AnalyticsApplication) getApplication();
//        Tracker mTracker = application.getDefaultTracker();
//        mTracker.setScreenName("Android-" + "Schedule Site Visit");
//        mTracker.enableAdvertisingIdCollection(true);
//        mTracker.send(new HitBuilders.ScreenViewBuilder().build());

        editText.setFocusable(false);
        mahindraClappPreference = MahindraClappPreference.getInstance(getApplicationContext());
//        new Service().getAllProperties(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android");
        objectAllProperty.addObserver(this);
        objectSetSiteVisitDate.addObserver(this);
        objectGetTimeSlot.addObserver(this);

//        sp_Property.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                editText.setText("");
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
    }

    @Override
    public void update(Observable o, Object arg) {

//        if (o instanceof ObjectAllProperty) {
//            if (objectAllProperty.getAllPropertyResponse().getSuccess()) {
//                for (int i = 0; i < objectAllProperty.getAllPropertyResponse().getData().getDetails().size(); i++) {
//                    propertiesList.add(objectAllProperty.getAllPropertyResponse().getData().getDetails().get(i).getName());
//                }
//                ArrayAdapter<String> categoriesAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, propertiesList);
//                sp_Property.setAdapter(categoriesAdapter);
//                progressBar.hideProgressBar();
//            } else {
//                Toast.makeText(this, "Something Went Wrong, Try Again Later", Toast.LENGTH_SHORT).show();
//                finish();
//            }
//        } else
        if (o instanceof ObjectSetSiteVisitDate) {
            btn_ScheduleVisit.setClickable(true);
            if (objectSetSiteVisitDate.getSetSiteVisitDateResponse().getSuccess()) {
                o.deleteObservers();
                startActivity(new Intent(ActivitySiteVisitSchedule.this, Activity_ThankYou.class).putExtra("Title", "Schedule a Site Visit").putExtra("Heading", "Thank You").putExtra("Detail", "Your request has been received \n our representative will contact you soon.").putExtra("Image", R.mipmap.thankyouiconclubhouse).putExtra("callBack", "SiteVisit"));
                progressBar.hideProgressBar();
                finish();
            } else {
                progressBar.hideProgressBar();
                Toast.makeText(this, objectSetSiteVisitDate.getSetSiteVisitDateResponse().getMessage(), Toast.LENGTH_SHORT).show();
            }

        } else if (o instanceof ObjectGetTimeSlot) {

            if (objectGetTimeSlot.getGetTimeSlotResponse().getSuccess()) {

                if (!list.isEmpty()) {
                    list.clear();
                }

                if (objectGetTimeSlot.getGetTimeSlotResponse().getData().size() == 0) {
                    Toast.makeText(this, "No time slot available.", Toast.LENGTH_SHORT).show();
                }

                for (int i = 0; i < objectGetTimeSlot.getGetTimeSlotResponse().getData().size(); i++) {
                    timeGetterSetter = new TimeGetterSetter(objectGetTimeSlot.getGetTimeSlotResponse().getData().get(i));
                    list.add(timeGetterSetter);
                }

                time_adapter = new Time_Adapter(list, ActivitySiteVisitSchedule.this);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(time_adapter);
            } else {
                Toast.makeText(this, objectGetTimeSlot.getGetTimeSlotResponse().getMessage(), Toast.LENGTH_SHORT).show();
            }
            progressBar.hideProgressBar();
        }
    }

    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int yearSelected, int monthOfYear, int dayOfMonth) {
            year = yearSelected;
            month = monthOfYear + 1;
            day = dayOfMonth;
            String mnth = (month < 10 ? "0" : "") + month;
            String date = (day < 10 ? "0" : "") + day;
            editText.setText(date + "/" + mnth + "/" + year);
            str_date = mnth + "/" + day + "/" + year;
//            String projectId = getId(sp_Property.getSelectedItem().toString());
            progressBar.showProgressBar(ActivitySiteVisitSchedule.this);
            new Service().getTimeSlot(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android", mahindraClappPreference.getData("Project"), str_date);
        }
    };

    @Override
    protected Dialog onCreateDialog(int id) {
        DatePickerDialog da = new DatePickerDialog(this, mDateSetListener, mYear, mMonth, mDay);
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, 0);
        Date newDate = c.getTime();
        da.getDatePicker().setMinDate(newDate.getTime());
        return da;
    }


    @OnClick(R.id.et_Date)
    public void onDateSelect(View view) {
        showDialog(1);
    }

    @OnClick(R.id.btn_ScheduleVisit)
    public void onButtonClick() {

        if (editText.getText().toString().equals("")) {
            Toast.makeText(this, "Please Select a valid Date", Toast.LENGTH_SHORT).show();
        } else if (timeSlot.equals("")) {
            Toast.makeText(this, "Please Select a valid time Slot", Toast.LENGTH_SHORT).show();
        } else {
            btn_ScheduleVisit.setClickable(false);
            progressBar.showProgressBar(ActivitySiteVisitSchedule.this);
            new Service().scheduleVisit(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android", str_date, timeSlot, mahindraClappPreference.getData("name"),mahindraClappPreference.getData("Project"), mahindraClappPreference.getData("Property"));
        }
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

    public class TimeGetterSetter {

        String Time;

        public String getTime() {
            return Time;
        }

        public void setTime(String time) {
            Time = time;
        }

        public TimeGetterSetter(String time) {

            Time = time;
        }

    }

    public class Time_Adapter extends RecyclerView.Adapter<Time_Adapter.MyViewHolder> {

        Context context;
        private java.util.List<TimeGetterSetter> List;
        int row_index;

        @Override
        public Time_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_time, parent, false);
            return new Time_Adapter.MyViewHolder(itemView);
        }

        public Time_Adapter(java.util.List<TimeGetterSetter> DataList, FragmentActivity activity) {
            this.List = DataList;
            context = activity;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            TextView tv_Time;
            LinearLayout ll_Time;

            public MyViewHolder(View view) {
                super(view);
                tv_Time = (TextView) view.findViewById(R.id.tv_Time);
                ll_Time = (LinearLayout) view.findViewById(R.id.ll_Time);
            }
        }

        @Override
        public void onBindViewHolder(final Time_Adapter.MyViewHolder holder, final int position) {
            final TimeGetterSetter detail = List.get(position);

            holder.ll_Time.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    row_index = position;
                    notifyDataSetChanged();
                    timeSlot = detail.getTime().toString();

                }
            });

            if (row_index == position) {
                holder.ll_Time.setBackgroundColor(Color.parseColor("#4d4948"));
                holder.tv_Time.setTextColor(Color.parseColor("#ffffff"));
            } else {
                holder.ll_Time.setBackgroundColor(Color.parseColor("#ffffff"));
                holder.tv_Time.setTextColor(Color.parseColor("#6d6e71"));
            }
            holder.tv_Time.setText(detail.getTime());
        }

        @Override
        public int getItemCount() {
            return List.size();
        }
    }

    public String getId(String selected) {
        String id = "";
        for (int i = 0; i < objectAllProperty.getAllPropertyResponse().getData().getDetails().size(); i++) {
            if (selected == objectAllProperty.getAllPropertyResponse().getData().getDetails().get(i).getName()) {
                id = objectAllProperty.getAllPropertyResponse().getData().getDetails().get(i).getProjectId();
                return id;
            }
        }
        return id;
    }

}