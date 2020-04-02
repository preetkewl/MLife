package com.example.focpc.mahindralifespaces.ui.activities.referal_section;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mlife.R;

import com.example.focpc.mahindralifespaces.utils.MlsUtils;

import java.util.List;

/**
 * Created by foc pc on 03-02-2018.
 */

public class DisclaimerRecyclerAdapter extends RecyclerView.Adapter<DisclaimerRecyclerAdapter.DisclaimerVH> {
    private String[] strings;
    private int safColor;
    private int blackColor;
    private Activity activity;
    private SpannableStringBuilder spSb, spSb2;
    private ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.BLUE);
    private ClickableSpan clickableSpan, clickableSpan2;


    public DisclaimerRecyclerAdapter(String[] strings, final Activity activity) {
        this.strings = strings;
        this.safColor = activity.getResources().getColor(R.color.text_color);
        this.blackColor = activity.getResources().getColor(R.color.text_color);
        this.activity = activity;
        spSb = new SpannableStringBuilder(activity.getText(R.string.disc2));
        spSb2 = new SpannableStringBuilder(activity.getText(R.string.disc11));

//        clickableSpan = new ClickableSpan() {
//            @Override
//            public void onClick(View view) {
//                MlsUtils.sendEmailIntent(activity);
//            }
//            @Override
//            public void updateDrawState(TextPaint ds) {
//                super.updateDrawState(ds);
//                ds.setUnderlineText(true);
//            }
//        };
//
//        clickableSpan2 =  new ClickableSpan() {
//            @Override
//            public void onClick(View view) {
//                MlsUtils.showInWebView("http://www.loyalie.com",activity);
//            }
//            @Override
//            public void updateDrawState(TextPaint ds) {
//                super.updateDrawState(ds);
//                ds.setUnderlineText(true);
//            }
//        };

    }

    @Override
    public DisclaimerVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.disclaimer_layout, parent, false);
        return new DisclaimerVH(v);
    }

    @Override
    public void onBindViewHolder(DisclaimerVH holder, int position) {
        if (position == 0) {
            holder.pointTV.setText(activity.getText(R.string.disc1));
        }
//        else if (position == 1) {
//            spSb.setSpan(clickableSpan2, 136, 151, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//            spSb.setSpan(colorSpan, 136, 151, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//            holder.pointTV.setText(spSb);
//            holder.pointTV.setMovementMethod(LinkMovementMethod.getInstance());
//            holder.pointTV.setHighlightColor(Color.TRANSPARENT);
//        }
//        else if (position == 10){
//            spSb2.setSpan(clickableSpan,129,148,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//            spSb2.setSpan(colorSpan, 129, 148, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//            holder.pointTV.setText(spSb2);
//            holder.pointTV.setMovementMethod(LinkMovementMethod.getInstance());
//            holder.pointTV.setHighlightColor(Color.TRANSPARENT);
//        }
        else {
            holder.pointTV.setText(strings[position]);
        }

        if ( position == 15 || position == 16) {
            holder.bulletView.setVisibility(View.GONE);
            holder.pointTV.setTextColor(safColor);
        }

//        else if (position == 10 || position == 16) {
//            holder.pointTV.setTextColor(blackColor);
//            holder.bulletView.setVisibility(View.GONE);
//        }

        else {
            holder.bulletView.setVisibility(View.VISIBLE);
            holder.pointTV.setTextColor(blackColor);
        }
    }

    @Override
    public int getItemCount() {
        return strings.length;
    }

    class DisclaimerVH extends RecyclerView.ViewHolder {
        AppCompatTextView pointTV;
        View bulletView;

        public DisclaimerVH(View itemView) {
            super(itemView);
            pointTV = itemView.findViewById(R.id.pointTV);
            bulletView = itemView.findViewById(R.id.bulletView);

//                itemView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        if (getAdapterPosition()==1)
//
//                    }
//                });

        }
    }
}
