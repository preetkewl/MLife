package com.mlife.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mlife.R;
import com.mlife.web.model.NoticeListData;

/**
 * Created by milagro on 9/4/2017.
 */

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.MyViewHolder> {

    Context context;
    private java.util.List<NoticeListData> list;

    @Override
    public NoticeAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_notice, parent, false);
        return new NoticeAdapter.MyViewHolder(itemView);
    }

    public NoticeAdapter(java.util.List<NoticeListData> DataList, FragmentActivity activity) {
        this.list = DataList;
        context = activity;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView Name, Members;

        public MyViewHolder(View view) {
            super(view);
            Name = (TextView) view.findViewById(R.id.tv_NoticeHeading);
            Members = (TextView) view.findViewById(R.id.tv_NoticeDetail);
        }
    }

    @Override
    public void onBindViewHolder(final NoticeAdapter.MyViewHolder holder, final int position) {
        holder.Name.setText(list.get(position).getNoticeTitle());
        holder.Members.setText("Posted: "+list.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}