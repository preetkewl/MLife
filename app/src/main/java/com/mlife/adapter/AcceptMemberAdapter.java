package com.mlife.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import android.widget.TextView;
import android.widget.Toast;

import com.mlife.R;
import com.mlife.utils.Constants;
import com.mlife.utils.MahindraClappPreference;
import com.mlife.web.api.Service;
import com.mlife.web.holder.DataHolder;
import com.mlife.web.holder.Response.ObjectUpdateGroupJoinRequest;
import com.mlife.web.model.MembersbyGroupData;
import com.squareup.picasso.Picasso;

/**
 * Created by milagro on 1/17/2018.
 */

public class AcceptMemberAdapter extends RecyclerView.Adapter<AcceptMemberAdapter.MyViewHolder> implements DataHolder, Observer {

    Context context;
    String groupId;
    private List<MembersbyGroupData> List;
    MahindraClappPreference mahindraClappPreference;

    @Override
    public AcceptMemberAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_accept_reject_user, parent, false);
        return new AcceptMemberAdapter.MyViewHolder(itemView);
    }

    public AcceptMemberAdapter(java.util.List<MembersbyGroupData> DataList, FragmentActivity activity , String grpId) {
        this.List = DataList;
        context = activity;
        groupId = grpId;
        mahindraClappPreference = MahindraClappPreference.getInstance(context);
        objectUpdateGroupJoinRequest.addObserver(this);
    }

    @Override
    public void update(Observable observable, Object o) {

        if (observable instanceof ObjectUpdateGroupJoinRequest) {
            if (objectUpdateGroupJoinRequest.getUpdateGroupJoinRequestResponse().getSuccess()) {
                Toast.makeText(context, objectUpdateGroupJoinRequest.getUpdateGroupJoinRequestResponse().getMessage(), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, objectUpdateGroupJoinRequest.getUpdateGroupJoinRequestResponse().getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_Name, tv_Request;
        public Button btn_Accept, btn_Reject;
        public ImageView iv_UserImage;

        public MyViewHolder(View view) {
            super(view);
            tv_Name = (TextView) view.findViewById(R.id.tv_UserName);
            iv_UserImage = (ImageView) view.findViewById(R.id.iv_userImage);
            tv_Request = (TextView) view.findViewById(R.id.tv_RequestResponse);
            btn_Accept = (Button) view.findViewById(R.id.btn_Accept);
            btn_Reject = (Button) view.findViewById(R.id.btn_Reject);
        }
    }

    @Override
    public void onBindViewHolder(final AcceptMemberAdapter.MyViewHolder holder, final int position) {
        final MembersbyGroupData detail = List.get(position);
        holder.tv_Name.setText(detail.getName());
        Picasso.with(context).load(Constants.baseUrl + detail.getImagePath()).into(holder.iv_UserImage);

        holder.btn_Accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.btn_Accept.setVisibility(View.GONE);
                holder.btn_Reject.setVisibility(View.GONE);
                holder.tv_Request.setVisibility(View.VISIBLE);
                holder.tv_Request.setText("Request Approved");
                new Service().updateGroupJoinRequest(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android", groupId, detail.getRequestId(), "1");

            }
        });

        holder.btn_Reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.btn_Accept.setVisibility(View.GONE);
                holder.btn_Reject.setVisibility(View.GONE);
                holder.tv_Request.setVisibility(View.VISIBLE);
                holder.tv_Request.setText("Request Deleted");
                new Service().updateGroupJoinRequest(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android", groupId, detail.getRequestId(), "0");
            }
        });
    }

    @Override
    public int getItemCount() {
        return List.size();
    }

}