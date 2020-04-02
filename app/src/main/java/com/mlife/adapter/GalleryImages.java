package com.mlife.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mlife.activities.Extras.Activity_ImageViewers_Slider;
import com.mlife.R;
import com.squareup.picasso.Picasso;

import java.io.Serializable;

/**
 * Created by milagro on 10/25/2017.
 */

public class GalleryImages extends RecyclerView.Adapter<GalleryImages.MyViewHolder> {

    Context context;
    private java.util.List<ProjectImageGetterSetter> List;

    @Override
    public GalleryImages.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_project_images, parent, false);
        return new GalleryImages.MyViewHolder(itemView);
    }

    public GalleryImages(java.util.List<ProjectImageGetterSetter> DataList, FragmentActivity activity) {
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
    public void onBindViewHolder(final GalleryImages.MyViewHolder holder, final int position) {
        final ProjectImageGetterSetter detail = List.get(position);
        Picasso.with(context).load(detail.getResource()).placeholder(R.mipmap.placeholderone).into(holder.image);
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, Activity_ImageViewers_Slider.class).putExtra("position", position).putExtra("mylist", (Serializable) List).putExtra("check","Gallery"));
            }
        });
    }

    @Override
    public int getItemCount() {
        return List.size();
    }

}