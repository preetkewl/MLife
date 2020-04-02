package com.mlife.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.mlife.R;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by milagro on 1/16/2018.
 */

public class GroupJoinedAdapter extends RecyclerView.Adapter<GroupJoinedAdapter.MyViewHolder> {

    Context context;
    private java.util.List<ExploreGroupGetterSetter> groupsList;
    private java.util.List<ExploreGroupGetterSetter> filterList;

    @Override
    public GroupJoinedAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_group_joined, parent, false);
        return new GroupJoinedAdapter.MyViewHolder(itemView);
    }

    public GroupJoinedAdapter(java.util.List<ExploreGroupGetterSetter> DataList, FragmentActivity activity) {
        this.groupsList = DataList;
        filterList = new ArrayList<>(groupsList);
        context = activity;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView Name, Members;
        public Button Join, Explore;

        public MyViewHolder(View view) {
            super(view);
            Name = (TextView) view.findViewById(R.id.tv_MyGroupHeading);
            Members = (TextView) view.findViewById(R.id.tv_MyGroupMembers);
        }
    }

    @Override
    public void onBindViewHolder(final GroupJoinedAdapter.MyViewHolder holder, final int position) {
        final ExploreGroupGetterSetter detail = groupsList.get(position);
        holder.Name.setText(detail.getGroupName());
        holder.Members.setText(detail.getMembers() + " Members");
    }

    @Override
    public int getItemCount() {
        return groupsList.size();
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        groupsList.clear();

        if (charText.length() == 0) {
            groupsList.addAll(filterList);
        } else {
            for (ExploreGroupGetterSetter wp : filterList) {
                if (wp.getGroupName().toLowerCase(Locale.getDefault()).contains(charText)) {
                    groupsList.add(wp);
                }
            }
        }
//        if (filterList.size() == 0) {
//                tvNoGroups.setVisibility(View.VISIBLE);
//        } else {
//                tvNoGroups.setVisibility(View.GONE);
//        }
        notifyDataSetChanged();
    }


}