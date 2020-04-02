package com.mlife.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mlife.web.model.OfferListData;
import com.mlife.R;

/**
 * Created by milagro on 9/5/2017.
 */

public class LocalOffersAdapter extends RecyclerView.Adapter<LocalOffersAdapter.MyViewHolder> {

    Context context;
    private java.util.List<OfferListData> List;

    @Override
    public LocalOffersAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_localoffers, parent, false);
        return new LocalOffersAdapter.MyViewHolder(itemView);
    }

    public LocalOffersAdapter(java.util.List<OfferListData> DataList, FragmentActivity activity) {
        this.List = DataList;
        context = activity;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView Heading, Detail, PostedBy, Date, Validity;

        public MyViewHolder(View view) {
            super(view);
            Heading = (TextView) view.findViewById(R.id.tv_offerHeadin_LO);
            Detail = (TextView) view.findViewById(R.id.tv_offerDetails_LO);
            PostedBy = (TextView) view.findViewById(R.id.tv_postedBy_LO);
            Date = (TextView) view.findViewById(R.id.tv_postedDate_LO);
            Validity = (TextView) view.findViewById(R.id.tv_offerValidity_LO);

        }
    }

    @Override
    public void onBindViewHolder(final LocalOffersAdapter.MyViewHolder holder, final int position) {
        final OfferListData detail = List.get(position);

        holder.Heading.setText(detail.getOfferTitle());
        holder.Detail.setText(detail.getOfferDec());
        holder.PostedBy.setText("Posted By: "+detail.getOfferPostedBy());
        holder.Date.setText(detail.getDate());
        holder.Validity.setText("Valid Till: "+detail.getDate());
    }

    @Override
    public int getItemCount() {
        return List.size();
    }

}