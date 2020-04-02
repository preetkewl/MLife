package com.mlife.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mlife.activities.Extras.Activity_MyProperty;
import com.mlife.R;

import java.util.List;

/**
 * Created by milagro on 10/11/2017.
 */

public class AmenitiesListingAdapter extends RecyclerView.Adapter<AmenitiesListingAdapter.MyViewHolder> {

//    String imageBaseUrl = "http://milagro.in/wip/mahindra-clapp/admin/assets/attachment/banners/";

    Context context;
    private java.util.List<String> list;
    Context activity = new Activity_MyProperty();

    @Override
    public AmenitiesListingAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_amenitieslisting, parent, false);
        return new AmenitiesListingAdapter.MyViewHolder(itemView);
    }

    public AmenitiesListingAdapter(List<String> DataList, Context activity) {
        this.list = DataList;
        context = activity;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView details;

        public MyViewHolder(View view) {
            super(view);
            details = (TextView) view.findViewById(R.id.tv_AmenityListing);
        }

    }

    @Override
    public void onBindViewHolder(final AmenitiesListingAdapter.MyViewHolder holder, final int position) {
         final String detail = list.get(position);
        holder.details.setText("> "+detail);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}

