package com.mlife.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mlife.activities.Extras.Activity_Maps;
import com.mlife.fragments.Fragment_Schedule;
import com.mlife.R;

public class SiteVisit_Adapter extends RecyclerView.Adapter<SiteVisit_Adapter.MyViewHolder> {

    Context context;
    private java.util.List<SiteVisitGetterSetter> List;

    @Override
    public SiteVisit_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_site_visit, parent, false);
        return new SiteVisit_Adapter.MyViewHolder(itemView);
    }

    public SiteVisit_Adapter(java.util.List<SiteVisitGetterSetter> DataList, FragmentActivity activity) {
        this.List = DataList;
        context = activity;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView Heading, Date, Status;
        public Button Cancle, Visit;
        public ImageView iv_Status;

        public MyViewHolder(View view) {
            super(view);
            Heading =  view.findViewById(R.id.tv_Location);
            Date =  view.findViewById(R.id.tv_Date);
            Status =  view.findViewById(R.id.tv_Status);
            iv_Status =  view.findViewById(R.id.iv_Status);
            Cancle =  view.findViewById(R.id.btn_Cancle);
            Visit =  view.findViewById(R.id.btn_ViewLocation);
        }
    }

    @Override
    public void onBindViewHolder(final SiteVisit_Adapter.MyViewHolder holder, final int position) {
        final SiteVisitGetterSetter detail = List.get(position);

        holder.Heading.setText("Site Visit to " + detail.getProperty());
        holder.Date.setText(detail.getDate());

        holder.Visit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, Activity_Maps.class).putExtra("Lat",Double.parseDouble(detail.getLat()) ).putExtra("Long",Double.parseDouble(detail.getLong()) ));
            }
        });

        if (detail.getStatus().equals("3")) {
            holder.Visit.setVisibility(View.GONE);
            holder.Cancle.setVisibility(View.GONE);
        }

        if (detail.getStatus().equals("2")) {
            holder.Visit.setVisibility(View.GONE);
            holder.Cancle.setVisibility(View.GONE);
        }

        holder.Cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment_Schedule.CancelVisit cancelVisit = new Fragment_Schedule.CancelVisit();
                cancelVisit.Dialogbox((Activity) context, detail.getId());
            }
        });

        if (detail.getStatus().equals("1")) {
            holder.iv_Status.setImageResource(R.mipmap.scheduledicon);
            holder.Status.setText("Scheduled");
        } else if (detail.getStatus().equals("3")) {
            holder.iv_Status.setImageResource(R.mipmap.cancelledicon);
            holder.Status.setText("Cancelled");
        } else if (detail.getStatus().equals("2")) {
            holder.iv_Status.setImageResource(R.mipmap.completedicon);
            holder.Status.setText("Completed");
        } else if (detail.getStatus().equals("0")) {
            holder.iv_Status.setImageResource(R.mipmap.scheduledicon);
            holder.Status.setText("Awaiting");
        }
    }

    @Override
    public int getItemCount() {
        return List.size();
    }


}

