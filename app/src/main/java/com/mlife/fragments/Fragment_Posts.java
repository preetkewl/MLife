package com.mlife.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mlife.activities.My_Community.Groups.Activity_GroupDetails;
import com.mlife.activities.My_Community.Groups.Activity_Post;
import com.mlife.adapter.PostAdapter;
import com.mlife.adapter.PostGetterSetter;
import com.mlife.utils.MahindraClappPreference;
import com.mlife.web.api.Service;
import com.mlife.web.holder.DataHolder;
import com.mlife.web.holder.Response.ObjectGetPosts;
import com.mlife.R;
import com.mlife.utils.DialogProgressBar;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Fragment_Posts extends Fragment implements Observer, DataHolder {

    View view;
    PostAdapter adapter;
    final static int uploadPost = 007;
    DialogProgressBar progressBar = new DialogProgressBar();
    Activity_GroupDetails activity_groupDetails;
    MahindraClappPreference mahindraClappPreference;
    List<PostGetterSetter> list = new ArrayList<>();

    @BindView(R.id.cv_CreatePost)
    CardView cv_CreatePost;

    @BindView(R.id.rv_Posts)
    RecyclerView recyclerView;

    @BindView(R.id.tv_NoPost)
    TextView tv_NoPost;

    @OnClick(R.id.tv_NewPost)
    public void newPost() {
        startActivityForResult(new Intent(getActivity(), Activity_Post.class).putExtra("callBack", "1"), uploadPost);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fragment__posts, container, false);
        ButterKnife.bind(this, view);
        activity_groupDetails = new Activity_GroupDetails();
        mahindraClappPreference = MahindraClappPreference.getInstance(getActivity());
        new Service().getPosts(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android", Activity_GroupDetails.groupId);
        objectGetPosts.addObserver(this);
        objectViewGroup.addObserver(this);
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (requestCode == uploadPost) {
                new Service().getPosts(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android", Activity_GroupDetails.groupId);
            }
        } catch (Exception e) {

        }
    }

    @Override
    public void update(Observable o, Object arg) {

        if (o instanceof ObjectGetPosts) {
            if (objectGetPosts.getGetPostsResponse().getSuccess()) {
                adapter = new PostAdapter(objectGetPosts.getGetPostsResponse().getData(), getActivity());
                if (objectGetPosts.getGetPostsResponse().getData().size() == 0) {
                    tv_NoPost.setVisibility(View.VISIBLE);
                    tv_NoPost.bringToFront();
                } else {
                    tv_NoPost.setVisibility(View.GONE);
                }

                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this.getActivity());
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setAdapter(adapter);
            }

            try {
                if (objectViewGroup.getViewGroupResponse().getData().getHasJoined().equals("0")) {
                    cv_CreatePost.setVisibility(View.GONE);
                } else {
                    cv_CreatePost.setVisibility(View.VISIBLE);
                }
            } catch (Exception e) {
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        new Service().getPosts(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android", Activity_GroupDetails.groupId);
    }

}