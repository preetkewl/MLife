package com.mlife.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mlife.R;
import com.mlife.web.model.LoadCommentsData;

/**
 * Created by milagro on 10/6/2017.
 */

public class LoadCommentsAdapter extends RecyclerView.Adapter<LoadCommentsAdapter.MyViewHolder> {

    Context context;
    private java.util.List<LoadCommentsData> List;

    @Override
    public LoadCommentsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_comment, parent, false);
        return new LoadCommentsAdapter.MyViewHolder(itemView);
    }

    public LoadCommentsAdapter(java.util.List<LoadCommentsData> DataList, FragmentActivity activity) {
        this.List = DataList;
        context = activity;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView Title, Comment, Date;
        public MyViewHolder(View view) {
                        super(view);
            Title = (TextView) view.findViewById(R.id.tv_commentPersonName);
            Comment = (TextView) view.findViewById(R.id.tv_CommentDetails);
            Date = (TextView) view.findViewById(R.id.tv_DateTime);
        }
    }

    @Override
    public void onBindViewHolder(final LoadCommentsAdapter.MyViewHolder holder, final int position) {
        final LoadCommentsData detail = List.get(position);

        holder.Title.setText(detail.getTitle());
        holder.Comment.setText(detail.getDescription());
        holder.Date.setText(detail.getCreatedOn());

    }

    @Override
    public int getItemCount() {
        return List.size();
    }

}