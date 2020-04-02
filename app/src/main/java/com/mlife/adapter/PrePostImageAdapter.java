package com.mlife.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mlife.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by milagro on 10/14/2017.
 */

public class PrePostImageAdapter extends RecyclerView.Adapter<PrePostImageAdapter.MyViewHolder> {

    Context context;
    private ArrayList<Uri> list;
   // Fragment fragment = new Fragment_PaymentHistory();


    @Override
    public PrePostImageAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_pre_post_image_view, parent, false);
        return new PrePostImageAdapter.MyViewHolder(itemView);
    }

    public PrePostImageAdapter(ArrayList<Uri> DataList, FragmentActivity activity) {
        this.list = DataList;
        context = activity;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public ImageView iv_PostImage;

        public MyViewHolder(View view) {
            super(view);
            iv_PostImage = (ImageView) view.findViewById(R.id.iv_PostImageView);
        }
    }

    @Override
    public void onBindViewHolder(final PrePostImageAdapter.MyViewHolder holder, final int position) {
        final Uri detail = list.get(position);
        Picasso.with(context).load(detail).placeholder(R.mipmap.placeholderone).into(holder.iv_PostImage);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}