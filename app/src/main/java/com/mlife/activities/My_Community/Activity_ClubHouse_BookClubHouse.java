package com.mlife.activities.My_Community;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.mlife.activities.ActivityHome;
import com.mlife.activities.ActivityNotification;
import com.mlife.activities.Extras.Activity_ThankYou;
import com.mlife.utils.BookClubHouseGetterSetter;
import com.mlife.web.api.Service;
import com.mlife.web.holder.Response.ObjectBookClubhouse;
import com.mlife.adapter.TimeGetterSetter;
import com.mlife.adapter.Time_Adapter;
import com.mlife.R;
import com.mlife.utils.MahindraClappPreference;
import com.mlife.utils.DialogProgressBar;
import com.mlife.web.holder.DataHolder;
import com.mlife.web.holder.Response.ObjectAmentieeList;
import com.mlife.web.holder.Response.ObjectGetAvailableTimeSlot;
import com.mlife.web.model.GetAvailableTimeSlotData;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Activity_ClubHouse_BookClubHouse extends AppCompatActivity implements DataHolder, Observer {

    @BindView(R.id.et_EventDate)
    EditText et_EventDate;

    @OnClick(R.id.iv_inc)
    public void incMember() {
        int i = Integer.valueOf(et_EventCount.getText().toString());
        ++i;
        et_EventCount.setText(String.valueOf(i));
    }

    @OnClick(R.id.iv_dec)
    public void decMember() {
        int i = Integer.valueOf(et_EventCount.getText().toString());
        --i;
        et_EventCount.setText(String.valueOf(i));
    }

    @BindView(R.id.rv_TimeSlot)
    RecyclerView recyclerView;

    @BindView(R.id.et_EventCount)
    EditText et_EventCount;

    @BindView(R.id.sp_Facility)
    Spinner sp_Facility;

    @BindView(R.id.tv_FacilityBooking)
    TextView tv_FacilityBooking;

    @BindView(R.id.tv_MaxBooking)
    TextView tv_MaxBooking;

    Time_Adapter time_adapter;
    int year, month, day, mYear, mMonth, mDay, total;
    DialogProgressBar progressBar = new DialogProgressBar();
    List<String> facilityList = new ArrayList<>();
    List<TimeGetterSetter> list = new ArrayList<>();
    MahindraClappPreference mahindraClappPreference;
    BookClubHouseGetterSetter bookClubHouseGetterSetter;
    List<BookClubHouseGetterSetter> dataList = new ArrayList<>();

    @Override
    protected void onResume() {
        super.onResume();
        FirebaseAnalytics mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle params = new Bundle();
        params.putString(FirebaseAnalytics.Param.ITEM_NAME, "Book Clubhouse Screen Android");
        mFirebaseAnalytics.setCurrentScreen(this, "Book Clubhouse Screen Android", null);
        mFirebaseAnalytics.logEvent("Book_Clubhouse_Screen_Android", params);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__book_club_house);
        ButterKnife.bind(this);
        tv_MaxBooking = (TextView) findViewById(R.id.tv_MaxBooking);

        et_EventDate.setFocusable(false);
        mahindraClappPreference = MahindraClappPreference.getInstance(getApplicationContext());
        objectAmentieeList.addObserver(this);
        objectBookClubhouse.addObserver(this);
        objectGetAvailableTimeSlot.addObserver(this);
        sp_Facility.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                et_EventDate.setText("Event Start Date");
                if (dataList.get(position).getAmenty_is_multiple().equals("1")) {
                    tv_FacilityBooking.setText("Facility (Multiple booking allowed)");
                } else {
                    tv_FacilityBooking.setText("Facility");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }

        });
        recyclerView.addOnItemTouchListener(new ActivityHome.RecyclerTouchListener(this, recyclerView, new ActivityHome.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                final GetAvailableTimeSlotData detail = objectGetAvailableTimeSlot.getGetAvailableTimeSlotResponse().getData().get(position);
                total = Integer.parseInt(detail.getAmentySeats()) - Integer.parseInt(detail.getBookings());
                tv_MaxBooking.setText("MAX LIMIT: " + total);
                et_EventCount.setText("" + total);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        progressBar.showProgressBar(this);
        new Service().amentieeList(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android", mahindraClappPreference.getData("Project"));
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof ObjectAmentieeList) {
            progressBar.hideProgressBar();

            if (objectAmentieeList.getAmentieeListResponse().getSuccess()) {
                for (int i = 0; i < objectAmentieeList.getAmentieeListResponse().getData().size(); i++) {
                    facilityList.add(objectAmentieeList.getAmentieeListResponse().getData().get(i).getAmentyName());
                    bookClubHouseGetterSetter = new BookClubHouseGetterSetter(objectAmentieeList.getAmentieeListResponse().getData().get(i).getAmentyId(), objectAmentieeList.getAmentieeListResponse().getData().get(i).getAmentyName(), objectAmentieeList.getAmentieeListResponse().getData().get(i).getAmenty_is_multiple(), objectAmentieeList.getAmentieeListResponse().getData().get(i).getTimeslotTime());
                    dataList.add(bookClubHouseGetterSetter);
                }

                if (dataList.isEmpty()) {
                    Toast.makeText(this, "No Amentiee found.", Toast.LENGTH_SHORT).show();
                    et_EventDate.setEnabled(false);
                }else {et_EventDate.setEnabled(true);}

                ArrayAdapter<String> categoriesAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, facilityList);
                sp_Facility.setAdapter(categoriesAdapter);
            }
        } else if (o instanceof ObjectBookClubhouse) {
            o.deleteObservers();
            progressBar.hideProgressBar();
            startActivity(new Intent(Activity_ClubHouse_BookClubHouse.this, Activity_ThankYou.class).putExtra("Title", "Clubhouse Booking").putExtra("Heading", "Thank You").putExtra("Detail", "Your booking request has been received.").putExtra("callBack", "Clubhouse"));
            finish();
        } else if (o instanceof ObjectGetAvailableTimeSlot) {
            progressBar.hideProgressBar();
            if (objectGetAvailableTimeSlot.getGetAvailableTimeSlotResponse().getSuccess()) {
                time_adapter = new Time_Adapter(objectGetAvailableTimeSlot.getGetAvailableTimeSlotResponse().getData(), Activity_ClubHouse_BookClubHouse.this);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(time_adapter);
                GetAvailableTimeSlotData detail = objectGetAvailableTimeSlot.getGetAvailableTimeSlotResponse().getData().get(0);
                total = Integer.parseInt(detail.getAmentySeats()) - Integer.parseInt(detail.getBookings());
                tv_MaxBooking.setText("MAX LIMIT: " + total);
                et_EventCount.setText("" + total);


            } else {
                Toast.makeText(Activity_ClubHouse_BookClubHouse.this, objectGetAvailableTimeSlot.getGetAvailableTimeSlotResponse().getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        DatePickerDialog da = new DatePickerDialog(this, mDateSetListener, mYear, mMonth, mDay);
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, 0);
        Date newDate = c.getTime();
        da.getDatePicker().setMinDate(newDate.getTime());
        return da;
    }

    @OnClick(R.id.btn_BookEvent)
    public void bookEvent() {

        et_EventCount.setError(null);
        et_EventDate.setError(null);

//        if (et_EventName.getText().toString().trim().length() == 0) {
//            et_EventName.setError("Not Valid");
//        } else
//
        if (et_EventDate.getText().toString().trim().length() == 0) {
            et_EventDate.setError("Not Valid");
        } else  if (Integer.valueOf(et_EventCount.getText().toString()) > total) {
            et_EventCount.setError("Not Valid");
        } else if (Time_Adapter.timeSlot.trim().length() == 0) {
            Toast.makeText(this, "Please select time slot.", Toast.LENGTH_SHORT).show();
        } else {
            progressBar.showProgressBar(Activity_ClubHouse_BookClubHouse.this);
            String id = getType(sp_Facility.getSelectedItem().toString());
            new Service().bookClubhouse(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android", mahindraClappPreference.getData("Project"), id, et_EventDate.getText().toString(), Time_Adapter.timeSlot, et_EventCount.getText().toString(), mahindraClappPreference.getData("bID"));
        }
    }

    public String getType(String groupName) {
        String check = "";
        for (int i = 0; i < dataList.size(); i++) {
            if (dataList.get(i).getAmenty_name().equals(groupName)) {
                check = dataList.get(i).getAmenty_id().toString();
            }
        }
        return check;
    }

    @OnClick(R.id.et_EventDate)
    public void onDateSelect(View view) {
        showDialog(1);
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

    @OnClick(R.id.iv_Back_ManageDitePlan)
    public void backClick() {
        finish();
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int yearSelected, int monthOfYear, int dayOfMonth) {
            year = yearSelected;
            month = monthOfYear + 1;
            day = dayOfMonth;
            String months = (month < 10 ? "0" : "") + month;
            String days = (day < 10 ? "0" : "") + day;
            et_EventDate.setText(year + "-" + months + "-" + days);
            String id = getType(sp_Facility.getSelectedItem().toString());
            progressBar.showProgressBar(Activity_ClubHouse_BookClubHouse.this);
            new Service().getAvailableTimeSlot(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android", id, et_EventDate.getText().toString());
        }
    };

}