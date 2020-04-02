package com.example.focpc.mahindralifespaces.ui.activities.reward_wallet;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
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

public class OneVisitRewardFragAdapter extends RecyclerView.Adapter<OneVisitRewardFragAdapter.ViewHolder> {
    List<VisitLeftItem> visitLeftList;
    private Context context;

    public OneVisitRewardFragAdapter(List<VisitLeftItem> visitLeftList) {
        this.visitLeftList = visitLeftList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.one_visit_singlerow, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.programNameTV.setText(visitLeftList.get(position).getProgramName());
        holder.dateTV.setText(getDate(visitLeftList.get(position).getProgramEndDate()));
        Picasso.with(context).load(MlsConstants.IMG_BASE_URL + visitLeftList.get(position).getVendorLogo()).into(holder.logoIV);
    }


    @Override
    public int getItemCount() {
        return visitLeftList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        RoundedImageView logoIV;
        TextView programNameTV;
        TextView dateTV;

        public ViewHolder(View itemView) {
            super(itemView);
            logoIV = itemView.findViewById(R.id.logoIV);
            programNameTV =  itemView.findViewById(R.id.programNameTV);
            dateTV =  itemView.findViewById(R.id.dateTV);

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
