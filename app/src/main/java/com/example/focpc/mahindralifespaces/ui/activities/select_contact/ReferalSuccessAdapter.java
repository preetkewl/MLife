package com.example.focpc.mahindralifespaces.ui.activities.select_contact;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.mlife.R;


import java.util.List;



public class ReferalSuccessAdapter extends RecyclerView.Adapter<ReferalSuccessAdapter.ReferSuccessVH> {
    private List<UserStatusItem> userStatusItems;

    public ReferalSuccessAdapter(List<UserStatusItem> userStatusItems) {
        this.userStatusItems = userStatusItems;
    }

    @Override
    public ReferSuccessVH onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.refer_success_single,parent,false);
        return new ReferSuccessVH(v);
    }

    @Override
    public void onBindViewHolder(ReferSuccessVH holder, int position) {

        if (position!= userStatusItems.size()-1) holder.bottomView.setVisibility(View.VISIBLE);
        else holder.bottomView.setVisibility(View.GONE);
        UserStatusItem userStatusItem = userStatusItems.get(position);
        holder.referNameTV.setText(userStatusItem.getUser_name());
        holder.referNumberTV.setText(userStatusItem.getUser_phone());
        if (userStatusItem.getUser_status()==1){
            holder.successImg.setVisibility(View.VISIBLE);
            holder.existingMemberTV.setVisibility(View.GONE);
        } else if (userStatusItem.getUser_status()==4 ) {
            holder.successImg.setVisibility(View.GONE);
            holder.existingMemberTV.setVisibility(View.VISIBLE);
            holder.existingMemberTV.setText("Invalid\n number");
        } else {
            holder.successImg.setVisibility(View.GONE);
            holder.existingMemberTV.setVisibility(View.VISIBLE);
            holder.existingMemberTV.setText("Existing\n member");
        }

    }

    @Override
    public int getItemCount() {
        return userStatusItems.size();
    }

    class ReferSuccessVH extends RecyclerView.ViewHolder {
        TextView referNameTV;
        TextView referNumberTV;
        TextView existingMemberTV;
        ImageView successImg;
        View bottomView;
        public ReferSuccessVH(View itemView) {
            super(itemView);
            bottomView = itemView.findViewById(R.id.bottomView);
            referNameTV = itemView.findViewById(R.id.referNameTV);
            referNumberTV = itemView.findViewById(R.id.referNumberTV);
            successImg = itemView.findViewById(R.id.successImg);
            existingMemberTV = itemView.findViewById(R.id.existingMemberTV);
        }

    }
}
