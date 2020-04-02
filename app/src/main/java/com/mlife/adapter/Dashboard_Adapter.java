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
import com.squareup.picasso.Picasso;

/**
 * Created by milagro on 8/10/2017.
 */

public class Dashboard_Adapter extends RecyclerView.Adapter<Dashboard_Adapter.MyViewHolder> {

    Context context;
    private java.util.List<NavigationGetterSetter> List;

    @Override
    public Dashboard_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_dashboardlisting, parent, false);
        return new Dashboard_Adapter.MyViewHolder(itemView);
    }

    public Dashboard_Adapter(java.util.List<NavigationGetterSetter> DataList, FragmentActivity activity) {
        this.List = DataList;
        context = activity;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView Title;
        public ImageView Image;

        public MyViewHolder(View view) {
            super(view);
            Title = (TextView) view.findViewById(R.id.tv_heading);
            Image = (ImageView) view.findViewById(R.id.iv_icon);
        }
    }

    @Override
    public void onBindViewHolder(final Dashboard_Adapter.MyViewHolder holder, final int position) {
        final NavigationGetterSetter detail = List.get(position);
        holder.Title.setText(detail.getTitle());
        Picasso.with(context).load(detail.getImage()).into(holder.Image);
    }

    @Override
    public int getItemCount() {
        return List.size();
    }

}