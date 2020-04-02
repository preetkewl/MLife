package com.mlife.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mlife.web.model.TicketListData;
import com.mlife.R;

/**
 * Created by milagro on 8/30/2017.
 */

public class Request_Adapter extends RecyclerView.Adapter<Request_Adapter.MyViewHolder> {

    Context context;
    private java.util.List<TicketListData> List;

    @Override
    public Request_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_my_request, parent, false);
        return new Request_Adapter.MyViewHolder(itemView);
    }

    public Request_Adapter(java.util.List<TicketListData> DataList, FragmentActivity activity) {
        this.List = DataList;
        context = activity;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView Name, Time, Message;

        public MyViewHolder(View view) {
            super(view);
            Name = (TextView) view.findViewById(R.id.tv_FeedbackHeading);
            Time = (TextView) view.findViewById(R.id.tv_FeedbackDate);
            Message = (TextView) view.findViewById(R.id.tv_FeedbackSubmitted);
        }
    }

    @Override
    public void onBindViewHolder(final Request_Adapter.MyViewHolder holder, final int position) {
        final TicketListData detail = List.get(position);
        holder.Name.setText(detail.getTicketDec());
        holder.Time.setText(detail.getDate());
//        holder.Message.setText(detail.getTicketDec());
    }

    @Override
    public int getItemCount() {
        return List.size();
    }

}