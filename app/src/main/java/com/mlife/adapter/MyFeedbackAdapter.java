package com.mlife.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mlife.R;

/**
 * Created by milagro on 8/30/2017.
 */

public class MyFeedbackAdapter extends RecyclerView.Adapter<MyFeedbackAdapter.MyViewHolder> {

    Context context;
    private java.util.List<MyFeedbackGetterSetter> List;

    @Override
    public MyFeedbackAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_feedback, parent, false);
        return new MyFeedbackAdapter.MyViewHolder(itemView);
    }

    public MyFeedbackAdapter(java.util.List<MyFeedbackGetterSetter> DataList, FragmentActivity activity) {
        this.List = DataList;
        context = activity;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView Heading, Date, Status;

        public MyViewHolder(View view) {
            super(view);
            Heading = (TextView) view.findViewById(R.id.tv_FeedbackHeading);
            Date = (TextView) view.findViewById(R.id.tv_FeedbackDate);
            Status = (TextView) view.findViewById(R.id.tv_FeedbackSubmitted);
        }
    }

    @Override
    public void onBindViewHolder(final MyFeedbackAdapter.MyViewHolder holder, final int position) {
        final MyFeedbackGetterSetter detail = List.get(position);
        holder.Heading.setText(detail.getHeading());
        holder.Date.setText(detail.getDate());

        if (detail.getStatus()) {
            holder.Status.setVisibility(View.VISIBLE);
        } else {
            holder.Status.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return List.size();
    }

}