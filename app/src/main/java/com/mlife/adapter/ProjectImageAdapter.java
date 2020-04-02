package com.mlife.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mlife.activities.ActivityMahindraLifespacesProjects;
import com.mlife.activities.Extras.Activity_ImageViewers_Slider;
import com.mlife.R;
import com.mlife.utils.Constants;
import com.squareup.picasso.Picasso;

import java.io.Serializable;

public class ProjectImageAdapter extends RecyclerView.Adapter<ProjectImageAdapter.MyViewHolder> {

    Context context;
    private java.util.List<ProjectImageGetterSetter> List;

    @Override
    public ProjectImageAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_project_images, parent, false);
        return new ProjectImageAdapter.MyViewHolder(itemView);
    }

    public ProjectImageAdapter(java.util.List<ProjectImageGetterSetter> DataList, FragmentActivity activity) {
        this.List = DataList;
        context = activity;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView image;

        public MyViewHolder(View view) {
            super(view);
            image = (ImageView) view.findViewById(R.id.iv_ProjectImage);
        }
    }

    @Override
    public void onBindViewHolder(final ProjectImageAdapter.MyViewHolder holder, final int position) {
        final ProjectImageGetterSetter detail = List.get(position);

        if (context instanceof ActivityMahindraLifespacesProjects) {
            Picasso.with(context).load(detail.getResource()).placeholder(R.mipmap.placeholderone).into(holder.image);
            holder.image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, Activity_ImageViewers_Slider.class).putExtra("position", position).putExtra("mylist", (Serializable) List).putExtra("check", "MLDL Construction"));}
            });
        } else {
            Picasso.with(context).load(Constants.baseUrl +detail.getImage()).placeholder(R.mipmap.placeholderone).into(holder.image);
            holder.image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, Activity_ImageViewers_Slider.class).putExtra("position", position).putExtra("mylist", (Serializable) List).putExtra("check", "Construction"));}
            });
        }
    }

    @Override
    public int getItemCount() {
        return List.size();
    }
}