package com.example.focpc.mahindralifespaces.ui.activities.reward_wallet;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class ActiveRewardFragAdapter extends RecyclerView.Adapter<ActiveRewardFragAdapter.ViewHolder> {
    List<RewardDetailsItem> rewardDetailsList;
    private Context context;

    public ActiveRewardFragAdapter(List<RewardDetailsItem> rewardDetailsList) {
        this.rewardDetailsList = rewardDetailsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v;
        v = inflater.inflate(R.layout.active_reward_singlerow, parent, false);
        context = parent.getContext();
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.programNameTV.setText(rewardDetailsList.get(position).getProg_name());
        holder.offerValidTV.setText(getDate(rewardDetailsList.get(position).getExpiry_date()));
        if (!TextUtils.isEmpty(rewardDetailsList.get(position).getClaim_direct()))
            holder.claimCodeTV.setText(rewardDetailsList.get(position).getClaim_direct());
        else  holder.claimCodeTV.setText("  NA  ");
        Picasso.with(context).load(MlsConstants.IMG_BASE_URL + rewardDetailsList.get(position).getVendor_logo()).into(holder.logoIV);

    }


    @Override
    public int getItemCount() {
        return rewardDetailsList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        RoundedImageView logoIV;
        TextView programNameTV;
        TextView offerValidTV;
        TextView claimCodeTV;

        public ViewHolder(View itemView) {
            super(itemView);
            logoIV = itemView.findViewById(R.id.logoIV);
            programNameTV = itemView.findViewById(R.id.programNameTV);
            offerValidTV = itemView.findViewById(R.id.offerValidTV);
            claimCodeTV = itemView.findViewById(R.id.claimCodeTV);
        }
    }

    private String getDate(String date) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return "Offer available till " + new SimpleDateFormat("dd MMM yyyy").format(format.parse(date));
        } catch (Exception e) {
            try {
                return "Offer available till " + date.split("\\s+")[0];
            } catch (Exception e1) {
                return "Offer available till " + date;
            }
        }
    }

}
