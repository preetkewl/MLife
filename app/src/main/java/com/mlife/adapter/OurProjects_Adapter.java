package com.mlife.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mlife.activities.ActivityMahindraLifespacesProjects;
import com.mlife.activities.Extras.Activity_MyProperty;
import com.mlife.utils.Constants;
import com.mlife.R;
import com.mlife.utils.DialogDropEnquiry;
import com.mlife.web.model.ProjectListData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class OurProjects_Adapter extends RecyclerView.Adapter<OurProjects_Adapter.MyViewHolder> {

    Context context;
    private java.util.List<ProjectListData> List;
    Context activity = new Activity_MyProperty();
    AmenitiesAdapter amenitiesAdapter;
    AmenitiesGetterSetter amenitiesGetterSetter;
    List<AmenitiesGetterSetter> getData = new ArrayList<>();

    @Override
    public OurProjects_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_ourprojects, parent, false);
        return new OurProjects_Adapter.MyViewHolder(itemView);
    }

    public OurProjects_Adapter(java.util.List<ProjectListData> DataList, FragmentActivity activity) {
        this.List = DataList;
        context = activity;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView Name, Location, Typology, Price;
        Button Explore, Enq;
        ImageView image,divider;
        LinearLayout linearLayout;
        DialogDropEnquiry _dropEnquiry;
        RecyclerView recyclerView;

        public MyViewHolder(View view) {
            super(view);
            Name = (TextView) view.findViewById(R.id.tv_ProjectName);
            Location = (TextView) view.findViewById(R.id.tv_ProjectLocation);
            Typology = (TextView) view.findViewById(R.id.tv_ProjectTopology);
            Price = (TextView) view.findViewById(R.id.tv_ProjectPrice);
            image = (ImageView) view.findViewById(R.id.iv_OurProjectImage);
            divider = (ImageView) view.findViewById(R.id.iv_Divider);
             Explore = (Button) view.findViewById(R.id.btn_ExploreProject);
            Enq = (Button) view.findViewById(R.id.btn_DropEnquiry);
            linearLayout = (LinearLayout) view.findViewById(R.id.ll_ImageCatch);
            _dropEnquiry = new DialogDropEnquiry();
            recyclerView = (RecyclerView) view.findViewById(R.id.rv_amanities);
        }

    }

    @Override
    public void onBindViewHolder(final OurProjects_Adapter.MyViewHolder holder, final int position) {
        final ProjectListData detail = List.get(position);

        amenitiesGetterSetter = new AmenitiesGetterSetter( List.get(position).getAmentiesimage(),List.get(position).getAmentiesshortname());
        getData.add(amenitiesGetterSetter);

        if (context instanceof Activity_MyProperty) {
            holder.Explore.setVisibility(View.GONE);
            holder.Enq.setVisibility(View.GONE);
            holder.linearLayout.setVisibility(View.GONE);
            holder.divider.setVisibility(View.GONE);
        }

        holder.Name.setText(detail.getMldlProjectName());
        holder.Location.setText(detail.getMldlProjectCity());
        holder.Typology.setText(detail.getMldlProjectTypology());
        holder.Price.setText(detail.getMldlProjectPrice());
        Picasso.with(context).load(Constants.banners + detail.getBanners().get(0)).placeholder(R.mipmap.placeholdertwo).into(holder.image);

        holder.Explore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {context.startActivity(new Intent(context, ActivityMahindraLifespacesProjects.class).putExtra("callBack","Property").putExtra("ProjectId",detail.getMldlProjectId()));}
        });

        holder.Enq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {holder._dropEnquiry.Dialogbox((Activity) context,detail.getMldlProjectId());}
        });

        amenitiesAdapter = new AmenitiesAdapter(getData.get(position).getDetail(),getData.get(position).getImage(), context);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayout.HORIZONTAL,  false);
        holder.recyclerView.setLayoutManager(layoutManager);
        holder.recyclerView.setAdapter(amenitiesAdapter);
    }

    @Override
    public int getItemCount() {
        return List.size();
    }

}

