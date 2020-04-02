package com.mlife.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mlife.web.model.GetServiceRequestsDetails;
import com.mlife.R;

/**
 * Created by milagro on 10/14/2017.
 */

public class GetServiceRequestsAdapter extends RecyclerView.Adapter<GetServiceRequestsAdapter.MyViewHolder> {

    Context context;
    private java.util.List<GetServiceRequestsDetails> List;

    @Override
    public GetServiceRequestsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_my_request, parent, false);
        return new GetServiceRequestsAdapter.MyViewHolder(itemView);
    }

    public GetServiceRequestsAdapter(java.util.List<GetServiceRequestsDetails> DataList, FragmentActivity activity) {
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
    public void onBindViewHolder(final GetServiceRequestsAdapter.MyViewHolder holder, final int position) {
        final GetServiceRequestsDetails detail = List.get(position);

        holder.Name.setText(detail.getDescription().toString());
        holder.Time.setText(detail.getDate());
        holder.Message.setText("");

    }

    @Override
    public int getItemCount() {
        return List.size();
    }

}