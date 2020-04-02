package com.mlife.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mlife.activities.My_Community.Groups.Activity_Comment;
import com.mlife.activities.My_Community.Groups.Activity_Post;
import com.mlife.utils.MahindraClappPreference;
import com.mlife.web.api.Service;
import com.mlife.web.holder.DataHolder;
import com.mlife.web.holder.Response.ObjectAddReport;
import com.mlife.web.model.GetPostsData;
import com.mlife.R;
import com.mlife.web.holder.Response.ObjectDeletePost;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyViewHolder> implements Observer, DataHolder {

    Context context;
    private java.util.List<GetPostsData> List;
    MahindraClappPreference mahindraClappPreference;
    PostImageAdapter adapter;
    PostAdapter postAdapter;
    CardView cardView;
    java.util.List<GetPostsData> list = new ArrayList<>();
    int result = 9;

    @Override
    public PostAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_group_post, parent, false);
        mahindraClappPreference = MahindraClappPreference.getInstance(context);
        objectDeletePost.addObserver(this);
        objectAddReport.addObserver(this);
        return new PostAdapter.MyViewHolder(itemView);
    }

    public PostAdapter(java.util.List<GetPostsData> DataList, FragmentActivity activity) {
        this.List = DataList;
        context = activity;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof ObjectAddReport) {
            if (objectAddReport.getAddReportResponse().getSuccess()) {
                Toast.makeText(context, "Post has been reported", Toast.LENGTH_SHORT).show();
            }
        } else if (o instanceof ObjectDeletePost) {
            if (objectDeletePost.getDeletePostResponse().getSuccess()) {
                Toast.makeText(context, "Post has been deleted", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView Heading, Date, Detail, LikeCount, CommentCount;
        RecyclerView recyclerView;
        LinearLayout LikeButton, CommentButton;
        ImageView ivPostOption, iv_like;
        CardView cvPostOption;
        LinearLayout ll_ReportPost, ll_DeletePost, ll_EditPost;

        public MyViewHolder(View view) {
            super(view);

            iv_like = (ImageView) view.findViewById(R.id.iv_like);
            ll_DeletePost = (LinearLayout) view.findViewById(R.id.ll_DeletePost);
            ll_ReportPost = (LinearLayout) view.findViewById(R.id.ll_ReportPost);
            ll_EditPost = (LinearLayout) view.findViewById(R.id.ll_EditPost);
            cvPostOption = (CardView) view.findViewById(R.id.cv_PostOptions);
            ivPostOption = (ImageView) view.findViewById(R.id.iv_PostOptions);
            Heading = (TextView) view.findViewById(R.id.tv_PostTitle);
            Date = (TextView) view.findViewById(R.id.tv_PostDate);
            Detail = (TextView) view.findViewById(R.id.tv_PostDetails);
            LikeCount = (TextView) view.findViewById(R.id.tv_LikeCount);
            CommentCount = (TextView) view.findViewById(R.id.tv_CommentCount);
            recyclerView = (RecyclerView) view.findViewById(R.id.rv_PostImaes);
            LikeButton = (LinearLayout) view.findViewById(R.id.ll_LikeButton);
            CommentButton = (LinearLayout) view.findViewById(R.id.ll_CommentButton);
        }
    }

    @Override
    public void onBindViewHolder(final PostAdapter.MyViewHolder holder, final int position) {
        final GetPostsData detail = List.get(position);

        if (cardView == null) {
            cardView = holder.cvPostOption;
        } else {
            cardView.setVisibility(View.GONE);
            cardView = holder.cvPostOption;
        }

        holder.ivPostOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.cvPostOption.getVisibility() == View.VISIBLE) {
                    holder.cvPostOption.setVisibility(View.GONE);
                } else {
                    holder.cvPostOption.setVisibility(View.VISIBLE);
                }
            }
        });

        holder.LikeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (detail.getLiked().equals("1")) {
                    int foo = Integer.parseInt(detail.getLikes());
                    foo--;
                    detail.setLikes(foo + "");
                    holder.LikeCount.setText(foo + " Likes");
                    holder.iv_like.setImageResource(R.mipmap.like);
                    detail.setLiked("0");
                } else if (detail.getLiked().equals("0")) {
                    int foo = Integer.parseInt(detail.getLikes());
                    foo++;
                    holder.LikeCount.setText(foo + " Likes");
                    detail.setLikes(foo + "");
                    holder.iv_like.setImageResource(R.mipmap.like_highlight);
                    detail.setLiked("1");
                }
                new Service().likePost(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android", detail.getId());
            }
        });

        if (detail.getLiked().equals("1")) {
            holder.iv_like.setImageResource(R.mipmap.like_highlight);
        }

        if (detail.getIsOwner()) {
            holder.ll_ReportPost.setVisibility(View.GONE);
        } else {
            holder.ll_DeletePost.setVisibility(View.GONE);
            holder.ll_EditPost.setVisibility(View.GONE);
        }

        holder.ll_DeletePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, List.size());
                holder.itemView.setVisibility(View.GONE);
                new Service().deletePost(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android", detail.getId());
            }
        });


        holder.ll_EditPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.cvPostOption.setVisibility(View.GONE);
                context.startActivity(new Intent(context, Activity_Post.class).putExtra("callBack","2").putExtra("PostId",detail.getId()).putExtra("PostDetails",detail.getDescription()).putExtra("PostAttachments",(Serializable) detail.getAttachments()));
            }
        });

        holder.ll_ReportPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Service().addReport(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android", detail.getId(), "");
            }
        });

        adapter = new PostImageAdapter(detail.getAttachments(), context);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        holder.recyclerView.setLayoutManager(mLayoutManager);
        holder.recyclerView.setItemAnimator(new DefaultItemAnimator());
        holder.recyclerView.setAdapter(adapter);

        holder.Heading.setText(detail.getTitle());
        holder.Date.setText("Posted " + detail.getCreatedOn());
        holder.Detail.setText(detail.getDescription());
        holder.LikeCount.setText(detail.getLikes() + " Likes");
        holder.CommentCount.setText(detail.getComments() + " Comments");
        holder.CommentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity) context).startActivityForResult((new Intent(context, Activity_Comment.class).putExtra("PostId", detail.getId()).putExtra("position", position)), result);
            }
        });
    }

    @Override
    public int getItemCount() {
        return List.size();
    }


}