package com.mlife.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mlife.R;
import com.mlife.web.model.projectOffersData;

/**
 * Created by milagro on 8/22/2017.
 */

public class Offers_Adapter extends RecyclerView.Adapter<Offers_Adapter.MyViewHolder> {

    Context context;
    private java.util.List<projectOffersData> List;

    @Override
    public Offers_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_offer_news, parent, false);
        return new Offers_Adapter.MyViewHolder(itemView);
    }

    public Offers_Adapter(java.util.List<projectOffersData> DataList, FragmentActivity activity) {
        this.List = DataList;
        context = activity;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView Title, Detail, Location;
        public ImageView Image;

        public MyViewHolder(View view) {
            super(view);
            Title = (TextView) view.findViewById(R.id.tv_ProjectOffers);
            Location = (TextView) view.findViewById(R.id.tv_LocationOffers);
            Detail = (TextView) view.findViewById(R.id.tv_DetailOffers);
            Image = (ImageView) view.findViewById(R.id.iv_offers);
        }
    }

    @Override
    public void onBindViewHolder(final Offers_Adapter.MyViewHolder holder, final int position) {
        final projectOffersData detail = List.get(position);
        holder.Title.setText(detail.getMldlProjectName());
        holder.Location.setText(detail.getMldlProjectCity());
        try {
            holder.Detail.setText(detail.getMldlProjectOffer().get(0).getDescription());
        } catch (Exception e) {
        }
    }

    @Override
    public int getItemCount() {
        return List.size();
    }

}