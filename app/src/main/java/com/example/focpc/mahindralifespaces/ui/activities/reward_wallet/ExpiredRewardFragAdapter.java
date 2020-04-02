package com.example.focpc.mahindralifespaces.ui.activities.reward_wallet;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.mlife.R;

import com.example.focpc.mahindralifespaces.utils.MlsConstants;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;


/**
 * Created by Ajofocaloid on 12-05-2017.
 */

public class ExpiredRewardFragAdapter extends RecyclerView.Adapter<ExpiredRewardFragAdapter.ViewHolder> {
    private List<RewardDetailsItem> rewardDetailsList;
    private Context context;

    public ExpiredRewardFragAdapter(List<RewardDetailsItem> rewardDetailsList) {
        this.rewardDetailsList = rewardDetailsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.expired_reward_singlerow, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RewardDetailsItem rewardDetails = rewardDetailsList.get(position);
        boolean isExpired = ( rewardDetails.getClaim_status()==3);
        holder.programNameTV.setText(rewardDetails.getProg_name());
        Picasso.with(context).load(MlsConstants.IMG_BASE_URL + rewardDetailsList.get(position).getVendor_logo()).into(holder.logoIV);
        if (isExpired ) {
            holder.expiredIV.setVisibility(View.VISIBLE);
            holder.claimedLL.setVisibility(View.INVISIBLE);
        } else {
            holder.expiredIV.setVisibility(View.INVISIBLE);
            holder.claimedLL.setVisibility(View.VISIBLE);
            holder.dateTV.setText(getDate(rewardDetails.getClaim_date()));
        }

    }


    @Override
    public int getItemCount() {
        return rewardDetailsList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        RoundedImageView logoIV;
        TextView programNameTV;
        ImageView expiredIV;
        LinearLayout claimedLL;
        TextView claimedTV;
        TextView dateTV;

        public ViewHolder(View itemView) {
            super(itemView);
            logoIV  = itemView.findViewById(R.id.logoIV);
            programNameTV  = itemView.findViewById(R.id.programNameTV);
            expiredIV  = itemView.findViewById(R.id.expiredIV);
            claimedLL  = itemView.findViewById(R.id.claimedLL);
            claimedTV  = itemView.findViewById(R.id.claimedTV);
            dateTV  = itemView.findViewById(R.id.dateTV);
        }
    }

    String getDate(String date) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return new SimpleDateFormat("dd MMM yyyy").format(format.parse(date));
        } catch (Exception e) {
            try {
                return date.split("\\s+")[0];
            } catch (Exception e1) {
                return date;
            }
        }
    }
}
