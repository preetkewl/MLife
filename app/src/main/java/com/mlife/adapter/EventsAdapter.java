package com.mlife.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mlife.web.model.EventListData;
import com.mlife.R;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.MyViewHolder> {

    Context context;
    private java.util.List<EventListData> list;

    @Override
    public EventsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_events, parent, false);
        return new EventsAdapter.MyViewHolder(itemView);
    }

    public EventsAdapter(java.util.List<EventListData> DataList, FragmentActivity activity) {
        this.list = DataList;
        context = activity;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView Name, Date, Response;

        public MyViewHolder(View view) {
            super(view);
            Name = (TextView) view.findViewById(R.id.tv_eventName);
            Date = (TextView) view.findViewById(R.id.tv_eventDate);
            Response = (TextView) view.findViewById(R.id.tv_eventResponse);
        }
    }

    @Override
    public void onBindViewHolder(final EventsAdapter.MyViewHolder holder, final int position) {
        final EventListData detail = list.get(position);
        holder.Name.setText(list.get(position).getEventTitle());
        holder.Date.setText("Posted: " + detail.getEventDate());

        if (detail.getUserEventInterest().equals("1")) {
            holder.Response.setVisibility(View.VISIBLE);
            holder.Response.setText("Your Response: Yes");
        }
        if (detail.getUserEventInterest().equals("2")) {
            holder.Response.setVisibility(View.VISIBLE);
            holder.Response.setText("Your Response: No");
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}