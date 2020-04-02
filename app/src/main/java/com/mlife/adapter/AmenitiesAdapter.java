package com.mlife.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mlife.activities.Extras.Activity_MyProperty;
import com.mlife.utils.Constants;
import com.mlife.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by milagro on 10/10/2017.
 */

public class AmenitiesAdapter  extends RecyclerView.Adapter<AmenitiesAdapter.MyViewHolder> {

//    String imageBaseUrl = "http://milagro.in/wip/mahindra-clapp/admin/assets/attachment/banners/";

    Context context;
    private java.util.List<AmenitiesGetterSetter> List;
    Context activity = new Activity_MyProperty();
    List<String> lImage = new ArrayList<>();
    List<String> ldetail = new ArrayList<>();


    public AmenitiesAdapter(List<String> detail, List<String> image, Context context) {
        this.ldetail = detail;
        this.lImage = image;
    }

    @Override
    public AmenitiesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_amenities, parent, false);
        return new AmenitiesAdapter.MyViewHolder(itemView);
    }

    public AmenitiesAdapter(java.util.List<AmenitiesGetterSetter> DataList, Context activity) {
        this.List = DataList;
        context = activity;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView details;
        ImageView icon;

        public MyViewHolder(View view) {
            super(view);
            details = (TextView) view.findViewById(R.id.tv_AmenityType);
            icon = (ImageView) view.findViewById(R.id.iv_AmenityIcon);
        }

    }

    @Override
    public void onBindViewHolder(final AmenitiesAdapter.MyViewHolder holder, final int position) {
       // final AmenitiesGetterSetter detail = List.get(position);
        holder.details.setText(ldetail.get(position));
        Picasso.with(context).load( Constants.amenities + lImage.get(position) ).into(holder.icon);
    }

    @Override
    public int getItemCount() {
        return ldetail.size();
    }

}

