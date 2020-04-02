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

public class RentSellAdapter extends RecyclerView.Adapter<RentSellAdapter.MyViewHolder> {

    Context context;
    private java.util.List<OfferListData> List;

    @Override
    public RentSellAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_sellrent, parent, false);
        return new RentSellAdapter.MyViewHolder(itemView);
    }

    public RentSellAdapter(java.util.List<OfferListData> DataList, FragmentActivity activity) {
        this.List = DataList;
        context = activity;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView Heading, Detail, PostedBy, Date;

        public MyViewHolder(View view) {
            super(view);
            Heading = (TextView) view.findViewById(R.id.tv_offerHeadin_SR);
            Detail = (TextView) view.findViewById(R.id.tv_offerDetails_SR);
            PostedBy = (TextView) view.findViewById(R.id.tv_postedBy_SR);
            Date = (TextView) view.findViewById(R.id.tv_postedDate_SR);

        }
    }

    @Override
    public void onBindViewHolder(final RentSellAdapter.MyViewHolder holder, final int position) {
        final OfferListData detail = List.get(position);

        holder.Heading.setText(detail.getOfferTitle());
        holder.Detail.setText(detail.getOfferDec());
        holder.PostedBy.setText("Posted By: "+detail.getOfferPostedBy());
        holder.Date.setText(detail.getDate());
    }

    @Override
    public int getItemCount() {
        return List.size();
    }

}