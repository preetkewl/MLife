package com.mlife.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mlife.R;
import com.mlife.web.model.TicketLogsData;

/**
 * Created by milagro on 8/30/2017.
 */

public class FeedbackChat_Adapter extends RecyclerView.Adapter<FeedbackChat_Adapter.MyViewHolder> {

    Context context;
    private java.util.List<TicketLogsData> List;

    @Override
    public FeedbackChat_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_feedbackchat, parent, false);
        return new FeedbackChat_Adapter.MyViewHolder(itemView);
    }

    public FeedbackChat_Adapter(java.util.List<TicketLogsData> DataList, FragmentActivity activity) {
        this.List = DataList;
        context = activity;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView Name, Time, Message;

        public MyViewHolder(View view) {
            super(view);
            Name = (TextView) view.findViewById(R.id.tv_Name);
            Time = (TextView) view.findViewById(R.id.tv_Date);
            Message = (TextView) view.findViewById(R.id.tv_Message);
        }
    }

    @Override
    public void onBindViewHolder(final FeedbackChat_Adapter.MyViewHolder holder, final int position) {
        final TicketLogsData detail = List.get(position);
        holder.Name.setText(detail.getTicketLogPostedBy());
        holder.Time.setText(detail.getDate());
        holder.Message.setText(detail.getTicketLogComment());
    }

    @Override
    public int getItemCount() {
        return List.size();
    }

}