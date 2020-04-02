package com.mlife.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mlife.R;
import com.mlife.web.model.GetAvailableTimeSlotData;

public class Time_Adapter extends RecyclerView.Adapter<Time_Adapter.MyViewHolder> {

    int row_index;
    Context context;
    public static String timeSlot;
    private java.util.List<GetAvailableTimeSlotData> List;

    @Override
    public Time_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_time, parent, false);
        return new Time_Adapter.MyViewHolder(itemView);

    }

    public Time_Adapter(java.util.List<GetAvailableTimeSlotData> DataList, FragmentActivity activity) {
        this.List = DataList;
        context = activity;
        timeSlot =   (DataList != null)? DataList.get(0).getAmentieeTimeslotTimeslot() : "";

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_Time;
        LinearLayout ll_Time;

        public MyViewHolder(View view) {
            super(view);
            tv_Time = (TextView) view.findViewById(R.id.tv_Time);
            ll_Time = (LinearLayout) view.findViewById(R.id.ll_Time);
        }
    }

    @Override
    public void onBindViewHolder(final Time_Adapter.MyViewHolder holder, final int position) {
        final GetAvailableTimeSlotData detail = List.get(position);

        holder.ll_Time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                row_index = position;
                notifyDataSetChanged();
                timeSlot = detail.getAmentieeTimeslotTimeslot();
            }
        });

        if (row_index == position) {
            holder.ll_Time.setBackgroundColor(Color.parseColor("#4d4948"));
            holder.tv_Time.setTextColor(Color.parseColor("#ffffff"));
        } else {
            holder.ll_Time.setBackgroundColor(Color.parseColor("#ffffff"));
            holder.tv_Time.setTextColor(Color.parseColor("#6d6e71"));
        }
        holder.tv_Time.setText(detail.getAmentieeTimeslotTimeslot());
    }

    @Override
    public int getItemCount() {
        return List.size();
    }

}