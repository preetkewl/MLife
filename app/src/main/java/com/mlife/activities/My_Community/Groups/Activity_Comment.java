package com.mlife.activities.My_Community.Groups;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;

import com.mlife.adapter.LoadCommentsAdapter;
import com.mlife.web.api.Service;
import com.mlife.web.holder.DataHolder;
import com.mlife.R;
import com.mlife.utils.MahindraClappPreference;
import com.mlife.utils.DialogProgressBar;
import com.mlife.web.holder.Response.ObjectAddComments;
import com.mlife.web.holder.Response.ObjectLoadComments;

import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Activity_Comment extends AppCompatActivity implements Observer, DataHolder {

    Intent intent;
    String postId;
    MahindraClappPreference mahindraClappPreference;
    DialogProgressBar progressBar = new DialogProgressBar();
    LoadCommentsAdapter adapter;

    @BindView(R.id.et_Comment)
    EditText etComment;

    @BindView(R.id.rv_Comments)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__comment);
        ButterKnife.bind(this);
        objectAddComments.addObserver(this);
        objectLoadComments.addObserver(this);
        mahindraClappPreference = MahindraClappPreference.getInstance(this);
        intent = getIntent();
        postId = intent.getStringExtra("PostId");
        new Service().loadComments(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android", postId);
    }

    @OnClick(R.id.btn_SendComment)
    public void sendComment() {
        etComment.setError(null);
       if (etComment.getText().toString().trim().length() == 0){
           etComment.setError("Can't be empty");
       }else {
           progressBar.showProgressBar(Activity_Comment.this);
           new Service().addComments(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android", postId, etComment.getText().toString());
       }
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof ObjectAddComments) {
            progressBar.hideProgressBar();
            if (objectAddComments.getAddCommentsResponse().getSuccess()) {
                if (objectAddComments.getAddCommentsResponse().getSuccess()) {
                    etComment.setText("");
                    new Service().loadComments(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android", postId);
                }
            }
        } else if (o instanceof ObjectLoadComments) {
            adapter = new LoadCommentsAdapter(objectLoadComments.getLoadCommentsResponse().getData(), Activity_Comment.this);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(adapter);
        }
    }

    @OnClick(R.id.iv_Back_ManageDitePlan)
    public void finishActivity(){
        finish();
    }

}