package com.example.focpc.mahindralifespaces.ui.activities.refer_now;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import com.mlife.R;

import com.example.focpc.mahindralifespaces.utils.MlsUtils;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.List;


public class ReferalProgramsAdapter extends RecyclerView.Adapter<ReferalProgramsAdapter.ViewHolder> {
    private List<ReferalItem> referalItemList;
    private ReferProgramSelectListener referProgramSelectListener;
    private Context mContext;
    private RadioButton lastChecked;
    private int lastCheckedPos;

    public ReferalProgramsAdapter(List<ReferalItem> referalItemList, ReferProgramSelectListener referProgramSelectListener, Context mContext) {
        this.referalItemList = referalItemList;
        this.referProgramSelectListener = referProgramSelectListener;
        this.mContext = mContext;
    }

    @Override
    public ReferalProgramsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.refer_now_single_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ReferalProgramsAdapter.ViewHolder holder, int position) {
        final ReferalItem referalItem = referalItemList.get(position);
        holder.programNameTV.setText(referalItem.getReferral_name());
        String validityText = "Valid till " + MlsUtils.convertDateFormat(referalItem.getReferral_end_date());
        holder.offerValidTV.setText(validityText);
        Picasso.with(mContext).load(referalItem.getReferral_image()).fit().centerInside().into(holder.logoIV);
        holder.referNowRadio.setChecked(referalItem.isSelected());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton cb = (RadioButton) holder.referNowRadio;
                cb.setChecked(!cb.isChecked());
                if (cb.isChecked()) {
                    if (lastChecked != null) {
                        lastChecked.setChecked(false);
                        referalItemList.get(lastCheckedPos).setSelected(false);
                    }

                    lastChecked = cb;
                    lastCheckedPos = holder.getAdapterPosition();
                } else lastChecked = null;

                referalItem.setSelected(holder.referNowRadio.isChecked());
                if (referalItem.isSelected())
                    referProgramSelectListener.onProgramSelected(referalItem);
                else referProgramSelectListener.onProgramSelected(null);
            }
        });

    }

    @Override
    public int getItemCount() {
        return referalItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RadioButton referNowRadio;
        TextView programNameTV;
        TextView offerValidTV;
        RoundedImageView logoIV;

        public ViewHolder(View itemView) {
            super(itemView);
            referNowRadio = itemView.findViewById(R.id.referNowRadio);
            programNameTV = itemView.findViewById(R.id.programNameTV);
            logoIV = itemView.findViewById(R.id.logoIV);
            offerValidTV = itemView.findViewById(R.id.offerValidTV);
        }
    }

    interface ReferProgramSelectListener {
        void onProgramSelected(ReferalItem referalItem);
    }

    public void setLastCheckToNull() {
        if (lastChecked != null) lastChecked = null;
        notifyDataSetChanged();
    }
}
