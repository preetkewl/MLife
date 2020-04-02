package com.mlife.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mlife.activities.My_Community.Groups.Activity_AcceptMembers;
import com.mlife.activities.My_Community.Groups.Activity_GroupDetails;
import com.mlife.R;
import com.mlife.web.model.MyGroupsData;

/**
 * Created by milagro on 9/4/2017.
 */

public class MyGroupsAdapter extends RecyclerView.Adapter<MyGroupsAdapter.MyViewHolder> {

    Context context;
    private java.util.List<MyGroupsData> List;

    @Override
    public MyGroupsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_my_group, parent, false);
        return new MyGroupsAdapter.MyViewHolder(itemView);
    }

    public MyGroupsAdapter(java.util.List<MyGroupsData> DataList, FragmentActivity activity) {
        this.List = DataList;
        context = activity;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView Name, Members, tv_PendingMembers;
        public LinearLayout ll_Heading, ll_PendingRequest;

        public MyViewHolder(View view) {
            super(view);
            Name = (TextView) view.findViewById(R.id.tv_MyGroupHeading);
            Members = (TextView) view.findViewById(R.id.tv_MyGroupMembers);
            tv_PendingMembers = (TextView) view.findViewById(R.id.tv_PendingMembers);
            ll_Heading = (LinearLayout) view.findViewById(R.id.ll_heading);
            ll_PendingRequest = (LinearLayout) view.findViewById(R.id.ll_PendingRequest);
        }
    }

    @Override
    public void onBindViewHolder(final MyGroupsAdapter.MyViewHolder holder, final int position) {
        final MyGroupsData detail = List.get(position);
        holder.Name.setText(detail.getName());
        holder.Members.setText("Members: " + detail.getMembers());
        holder.tv_PendingMembers.setText("Requests: " + detail.getRequests());

        holder.ll_PendingRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, Activity_AcceptMembers.class).putExtra("GroupId", detail.getId()));
            }
        });

        holder.ll_Heading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, Activity_GroupDetails.class).putExtra("GroupId", detail.getId()).putExtra("GroupType", detail.getIsPublic()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return List.size();
    }

}