package com.mlife.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mlife.activities.ActivityMahindraLifespacesProjects;
import com.mlife.activities.Extras.Activity_OurProjects_WebView;
import com.mlife.utils.Constants;
import com.mlife.web.holder.DataHolder;
import com.mlife.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by milagro on 11/8/2017.
 */

public class ViewPagerAdapter_NavigationDashboard extends PagerAdapter {

    private List<String> images;
    private LayoutInflater inflater;
    private Context context;

    public ViewPagerAdapter_NavigationDashboard(Context context, List<String> images) {
        this.context = context;
        this.images = images;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public Object instantiateItem(ViewGroup view, final int position) {
        View myImageLayout = inflater.inflate(R.layout.image_item, view, false);
        ImageView myImage = (ImageView) myImageLayout.findViewById(R.id.iv);
        Picasso.with(context).load(Constants.banners + images.get(position)).placeholder(R.mipmap.placeholdertwo).into(myImage);
        myImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DataHolder.objectHomeBannerList.getHomeBannerListResponse().getData().get(position).getBannertype().equals("0")) {
                    context.startActivity(new Intent(context, ActivityMahindraLifespacesProjects.class).putExtra("callBack", "Offers").putExtra("ProjectId", DataHolder.objectHomeBannerList.getHomeBannerListResponse().getData().get(position).getProjectId()));
                } else if (DataHolder.objectHomeBannerList.getHomeBannerListResponse().getData().get(position).getBannertype().equals("1")) {
                    context.startActivity(new Intent(context, Activity_OurProjects_WebView.class).putExtra("ProjectLink", DataHolder.objectHomeBannerList.getHomeBannerListResponse().getData().get(position).getBannerurl()));
                } else if (DataHolder.objectHomeBannerList.getHomeBannerListResponse().getData().get(position).getBannertype().equals("2")) {
                    String url = DataHolder.objectHomeBannerList.getHomeBannerListResponse().getData().get(position).getBannerurl();
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    context.startActivity(i);
                }
            }
        });
        view.addView(myImageLayout, 0);
        return myImageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

}