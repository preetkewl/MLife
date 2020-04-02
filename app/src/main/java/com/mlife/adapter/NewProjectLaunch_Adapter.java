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

/**
 * Created by milagro on 8/11/2017.
 */

public class NewProjectLaunch_Adapter extends RecyclerView.Adapter<NewProjectLaunch_Adapter.MyViewHolder> {

    Context context;
    private java.util.List<NewProjectLaunchGetterSetter> List;

    @Override
    public NewProjectLaunch_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_newprojectlaunch, parent, false);
        return new NewProjectLaunch_Adapter.MyViewHolder(itemView);
    }

    public NewProjectLaunch_Adapter(java.util.List<NewProjectLaunchGetterSetter> DataList, FragmentActivity activity) {
        this.List = DataList;
        context = activity;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView Title,Detail;
        public ImageView Image;

        public MyViewHolder(View view) {
            super(view);
            Title = (TextView) view.findViewById(R.id.tv_ProjectName);
            Detail = (TextView) view.findViewById(R.id.tv_ProjectDetail);
            Image = (ImageView) view.findViewById(R.id.iv_ProjectImage);
        }
    }

    @Override
    public void onBindViewHolder(final NewProjectLaunch_Adapter.MyViewHolder holder, final int position) {
        final NewProjectLaunchGetterSetter detail = List.get(position);
        holder.Title.setText(detail.getHeading());
        holder.Detail.setText(detail.getTitle());
        holder.Image.setImageResource(detail.getImage());
    }

    @Override
    public int getItemCount() {
        return List.size();
    }

}