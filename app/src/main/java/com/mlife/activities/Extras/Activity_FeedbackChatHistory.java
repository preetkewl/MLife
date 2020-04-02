package com.mlife.activities.Extras;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.mlife.activities.ActivityHome;
import com.mlife.activities.ActivityNotification;
import com.mlife.web.api.Service;
import com.mlife.web.holder.Response.ObjectAddTicket;
import com.mlife.adapter.FedbackChat_GetterSetter;
import com.mlife.adapter.FeedbackChat_Adapter;
import com.mlife.R;
import com.mlife.utils.MahindraClappPreference;
import com.mlife.web.holder.DataHolder;
import com.mlife.web.holder.Response.ObjectTicketLogs;
import com.mlife.web.model.TicketLogsData;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Activity_FeedbackChatHistory extends AppCompatActivity implements Observer, DataHolder {

    Intent intent;
    FeedbackChat_Adapter feedbackChat_adapter;
    MahindraClappPreference mahindraClappPreference;
    List<FedbackChat_GetterSetter> list = new ArrayList<>();

    List<TicketLogsData> listTicketHistory;

    @BindView(R.id.rv_FeedbackChat)
    RecyclerView recyclerView;

    @BindView(R.id.et_Message)
    EditText et_Message;

    @BindView(R.id.tv_Heading)
    TextView tvHeading;

    @OnClick(R.id.btn_Send)
    public void sendMessage(View view) {
        et_Message.setError(null);
        if (et_Message.getText().toString().isEmpty()) {
            et_Message.setError("Can't be empty");
        } else {
            new Service().addTicketLog(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android", intent.getStringExtra("id"), et_Message.getText().toString());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__feedback_chat_history);
        ButterKnife.bind(this);
        intent = getIntent();
        objectTicketLogs.addObserver(this);
        objectAddTicketLog.addObserver(this);
        objectAddTicket.addObserver(this);
        tvHeading.setText(intent.getStringExtra("heading"));
        mahindraClappPreference = MahindraClappPreference.getInstance(Activity_FeedbackChatHistory.this);
        new Service().ticketLogs(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android", intent.getStringExtra("id"));
    }

    @Override
    public void update(Observable o, Object arg) {

        if (o instanceof ObjectTicketLogs) {
            listTicketHistory = objectTicketLogs.getTicketLogsResponse().getData();
            if (objectTicketLogs.getTicketLogsResponse().getSuccess()) {
                feedbackChat_adapter = new FeedbackChat_Adapter(listTicketHistory, Activity_FeedbackChatHistory.this);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(feedbackChat_adapter);
            }
        }else if(o instanceof ObjectAddTicket){
            if(objectAddTicket.getAddTicketResponse().getSuccess()){
//                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
//                Calendar cal = Calendar.getInstance();
//                String date =  cal.get(Calendar.DAY_OF_WEEK)+" "+dateFormat.format(cal);
                TicketLogsData fds = new TicketLogsData("", "", "", et_Message.getText().toString(), "Today", "", mahindraClappPreference.getData("Name"));
                et_Message.setText("");
                listTicketHistory.add(fds);
                feedbackChat_adapter.notifyDataSetChanged();
            }
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

}