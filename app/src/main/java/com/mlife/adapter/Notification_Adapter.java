package com.mlife.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mlife.R;
import com.mlife.web.model.NotificationListData;

/**
 * Created by milagro on 8/22/2017.
 */

public class Notification_Adapter extends RecyclerView.Adapter<Notification_Adapter.MyViewHolder> {

    Context context;
    private java.util.List<NotificationListData> List;

    @Override
    public Notification_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_notification, parent, false);
        return new Notification_Adapter.MyViewHolder(itemView);
    }

    public Notification_Adapter(java.util.List<NotificationListData> DataList, FragmentActivity activity) {
        this.List = DataList;
        context = activity;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView Title,Detail;

        public MyViewHolder(View view) {
            super(view);
            Title = (TextView) view.findViewById(R.id.tv_NotificationHeading);
            Detail = (TextView) view.findViewById(R.id.tv_NotificationDetails);
        }
    }

    @Override
    public void onBindViewHolder(final Notification_Adapter.MyViewHolder holder, final int position) {
        final NotificationListData detail = List.get(position);
        holder.Title.setText(detail.getNotificationComment());
        holder.Detail.setText(detail.getDate());
    }

    @Override
    public int getItemCount() {
        return List.size();
    }

}