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


public class MyCommunnity_Adapter extends RecyclerView.Adapter<MyCommunnity_Adapter.MyViewHolder> {

    Context context;
    private java.util.List<MyCommunityGetterSetter> List;

    @Override
    public MyCommunnity_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_my_community, parent, false);
        return new MyCommunnity_Adapter.MyViewHolder(itemView);
    }

    public MyCommunnity_Adapter(java.util.List<MyCommunityGetterSetter> DataList, FragmentActivity activity) {
        this.List = DataList;
        context = activity;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView Title;
        public ImageView icon;

        public MyViewHolder(View view) {
            super(view);
            Title = (TextView) view.findViewById(R.id.tv_Title);
            icon = (ImageView) view.findViewById(R.id.iv_Icon);
        }
    }

    @Override
    public void onBindViewHolder(final MyCommunnity_Adapter.MyViewHolder holder, final int position) {
        final MyCommunityGetterSetter detail = List.get(position);
        holder.Title.setText(detail.getTitle());
        holder.icon.setImageResource(detail.getIcon());
    }

    @Override
    public int getItemCount() {
        return List.size();
    }

}