package com.mlife.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.*;

import com.mlife.activities.ActivityValueAddedServiceMyInterior;
import com.mlife.R;
import com.mlife.web.model.GetProductData;

public class ValueAddedServicesAdapter extends RecyclerView.Adapter<ValueAddedServicesAdapter.MyViewHolder> {

    Context context;
    private List<GetProductData> list;

    @Override
    public ValueAddedServicesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_valueaddedservice, parent, false);
        return new ValueAddedServicesAdapter.MyViewHolder(itemView);
    }

    public ValueAddedServicesAdapter(List<GetProductData> DataList, FragmentActivity activity) {
        this.list = DataList;
        context = activity;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_vasCategory;
        Button btn_exploreCategory;
        public MyViewHolder(View view) {
            super(view);
            tv_vasCategory = (TextView) view.findViewById(R.id.tv_vasCategory);
            btn_exploreCategory = (Button) view.findViewById(R.id.btn_exploreCategory);
        }
    }

    @Override
    public void onBindViewHolder(final ValueAddedServicesAdapter.MyViewHolder holder, final int position) {
        final GetProductData detail = list.get(position);
        holder.tv_vasCategory.setText(detail.getVasProductName().toUpperCase());
        holder.btn_exploreCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, ActivityValueAddedServiceMyInterior.class).putExtra("Position",position).putExtra("Size",list.size()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}