package com.mlife.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mlife.R;
import com.mlife.utils.Constants;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by milagro on 11/10/2017.
 */

public class ViewPagerAdapter_Classified  extends PagerAdapter {

    private List<String> images;
    private LayoutInflater inflater;
    private Context context;


    public ViewPagerAdapter_Classified(Context context, List<String> images) {
        this.context = context;
        this.images=images;
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
    public Object instantiateItem(ViewGroup view, int position) {
        View myImageLayout = inflater.inflate(R.layout.image_item, view, false);
        ImageView myImage = (ImageView) myImageLayout.findViewById(R.id.iv);
        Picasso.with(context).load(Constants.classified + images.get(position)).placeholder(R.mipmap.placeholdertwo).into(myImage);
        view.addView(myImageLayout, 0);
        return myImageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

}