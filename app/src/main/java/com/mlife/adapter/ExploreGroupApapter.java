package com.mlife.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.mlife.activities.My_Community.Groups.Activity_GroupDetails;
import com.mlife.R;

/**
 * Created by milagro on 9/4/2017.
 */

public class ExploreGroupApapter extends RecyclerView.Adapter<ExploreGroupApapter.MyViewHolder> {

    Context context;
    private java.util.List<ExploreGroupGetterSetter> List;

    @Override
    public ExploreGroupApapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_explore_group, parent, false);
        return new ExploreGroupApapter.MyViewHolder(itemView);
    }

    public ExploreGroupApapter(java.util.List<ExploreGroupGetterSetter> DataList, FragmentActivity activity) {
        this.List = DataList;
        context = activity;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView Name, Members;
        public Button Join, Explore;

        public MyViewHolder(View view) {
            super(view);
            Name = (TextView) view.findViewById(R.id.tv_ExploreGroupHeading);
            Members = (TextView) view.findViewById(R.id.tv_ExploreGroupMembers);
            Explore = (Button) view.findViewById(R.id.btn_ExploreGroupExplore);
            Join = (Button) view.findViewById(R.id.btn_ExploreGroupJoinGroup);

        }
    }

    @Override
    public void onBindViewHolder(final ExploreGroupApapter.MyViewHolder holder, final int position) {
        final ExploreGroupGetterSetter detail = List.get(position);
        holder.Name.setText(detail.getGroupName());
        holder.Members.setText(detail.getMembers() + " Members");
        holder.Explore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, Activity_GroupDetails.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return List.size();
    }

}