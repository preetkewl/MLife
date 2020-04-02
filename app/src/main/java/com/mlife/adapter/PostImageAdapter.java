package com.mlife.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mlife.utils.Constants;
import com.mlife.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by milagro on 9/11/2017.
 */

public class PostImageAdapter extends RecyclerView.Adapter<PostImageAdapter.MyViewHolder> {

    Context context;
    private List<String> List;

    @Override
    public PostImageAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_post_images, parent, false);
        return new PostImageAdapter.MyViewHolder(itemView);
    }

    public PostImageAdapter(List<String> attachments, Context activity) {
        this.List = attachments;
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
    public void onBindViewHolder(final PostImageAdapter.MyViewHolder holder, final int position) {
        final String detail = List.get(position);

        Picasso.with(context).load(Constants.groupImages + detail.trim()).placeholder(R.mipmap.placeholderone).into(holder.image);
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                context.startActivity(new Intent(context, Activity_ImageViewer.class).putExtra("Image", detail));
            }
        });

    }

    @Override
    public int getItemCount() {
        return List.size();
    }

}