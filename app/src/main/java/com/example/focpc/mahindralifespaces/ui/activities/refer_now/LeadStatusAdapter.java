package com.example.focpc.mahindralifespaces.ui.activities.refer_now;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mlife.R;

import java.util.List;

/**
 * Created by foc pc on 06-12-2017.
 */

public class LeadStatusAdapter extends RecyclerView.Adapter<LeadStatusAdapter.ReferalStatusVH> {
    private Context mContext;
    List<LeadItem> referedUsers;

    public LeadStatusAdapter(Context mContext, List<LeadItem> referedUsers) {
        this.mContext = mContext;
        this.referedUsers = referedUsers;
    }

    @Override
    public ReferalStatusVH onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.lead_status_list_item, parent, false);
        return new ReferalStatusVH(v);
    }

    @Override
    public void onBindViewHolder(ReferalStatusVH holder, int position) {
        LeadItem leadItem = referedUsers.get(position);
        holder.referalNameTV.setText(leadItem.getUser_name());
        holder.referalProgramTV.setText(leadItem.getReferral_name());
        holder.referalStatusStatusTv.setText(leadItem.getStatus());

    }


    @Override
    public int getItemCount() {
        return referedUsers.size();
    }

    class ReferalStatusVH extends RecyclerView.ViewHolder {
        TextView referalNameTV,referalStatusStatusTv,referalProgramTV;

        public ReferalStatusVH(View itemView) {
            super(itemView);
            referalNameTV = itemView.findViewById(R.id.referalNameTV);
            referalProgramTV = itemView.findViewById(R.id.referalProgramTV);
            referalStatusStatusTv = itemView.findViewById(R.id.referalStatusStatusTv);
        }
    }
}
