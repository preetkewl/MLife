package com.mlife.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bogdwellers.pinchtozoom.ImageMatrixTouchHandler;
import com.mlife.utils.Constants;
import com.mlife.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by milagro on 11/9/2017.
 */

public class ViewPagerAdapter_ImageViewer extends PagerAdapter {

    private List<ProjectImageGetterSetter> images;
    private LayoutInflater inflater;
    private Context context;
    private int check;

    public ViewPagerAdapter_ImageViewer(Context context, ArrayList<ProjectImageGetterSetter> images, int check) {
        this.context = context;
        this.images = images;
        inflater = LayoutInflater.from(context);
        this.check = check;
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
        View myImageLayout = inflater.inflate(R.layout.view_imageviewer, view, false);
        ImageView myImage = (ImageView) myImageLayout.findViewById(R.id.iv_Imageview);
        final RelativeLayout layout = (RelativeLayout) myImageLayout.findViewById(R.id.rr_readMore);
        Button btn_readMore = (Button) myImageLayout.findViewById(R.id.btn_readMore);
        TextView tv_towerTitle = (TextView) myImageLayout.findViewById(R.id.tv_towerTitle);
        TextView tv_projectDetails = (TextView) myImageLayout.findViewById(R.id.tv_projectDetails);

        btn_readMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setTitle("Details").setMessage(images.get(position).getDescription());
                alertDialogBuilder.show();

            }
        });

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.setVisibility(View.GONE);
            }
        });

        if (check == 0) {
            btn_readMore.setVisibility(View.VISIBLE);
            Picasso.with(context).load(images.get(position).getResource()).placeholder(R.mipmap.placeholdertwo).into(myImage);
            tv_towerTitle.setText(images.get(position).getCaption());
        } else if (check == 1) {
            Picasso.with(context).load(images.get(position).getResource()).placeholder(R.mipmap.placeholdertwo).into(myImage);
            tv_towerTitle.setText(images.get(position).getCaption());
        } else if (check == 2) {
            btn_readMore.setVisibility(View.VISIBLE);
            Picasso.with(context).load(Constants.baseUrl + images.get(position).getImage()).placeholder(R.mipmap.placeholdertwo).into(myImage);
            tv_towerTitle.setText(images.get(position).getCaption());
            tv_projectDetails.setText(images.get(position).getDescription());
        }

        myImage.setOnTouchListener(new ImageMatrixTouchHandler(view.getContext()));
        view.addView(myImageLayout, 0);
        return myImageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

}