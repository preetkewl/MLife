package com.mlife.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mlife.R;
import com.mlife.utils.Constants;
import com.mlife.web.model.MembersbyGroupData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by milagro on 9/7/2017.
 */

public class MembersAdapter extends RecyclerView.Adapter<MembersAdapter.MyViewHolder> {

    Context context;
    private java.util.List<MembersbyGroupData> List;
    private java.util.List<MembersbyGroupData> arraylist;

    @Override
    public MembersAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_members, parent, false);
        return new MembersAdapter.MyViewHolder(itemView);
    }

    public MembersAdapter(java.util.List<MembersbyGroupData> DataList, FragmentActivity activity) {
        this.List = DataList;
        arraylist = new ArrayList<>(List);
        context = activity;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView Name;
        public ImageView Image;

        public MyViewHolder(View view) {
            super(view);
            Name = (TextView) view.findViewById(R.id.tv_MemberName);
            Image = (ImageView) view.findViewById(R.id.iv_MemberImage);
        }
    }

    @Override
    public void onBindViewHolder(final MembersAdapter.MyViewHolder holder, final int position) {
        final MembersbyGroupData detail = List.get(position);
        holder.Name.setText(detail.getName());
        Picasso.with(context).load(Constants.baseUrl + detail.getImagePath()).placeholder(R.mipmap.search_members).into(holder.Image);
    }

    @Override
    public int getItemCount() {
        return List.size();
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        List.clear();

        if (charText.length() == 0) {
            List.addAll(arraylist);
        } else {
            for (MembersbyGroupData wp : arraylist) {
                if (wp.getName().toLowerCase(Locale.getDefault()).contains(charText)) {
                    List.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }



}