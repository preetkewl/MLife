package com.example.focpc.mahindralifespaces.ui.activities.promote_us_contact;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mlife.R;
import com.example.focpc.mahindralifespaces.ui.activities.select_contact.ContactItem;
import com.example.focpc.mahindralifespaces.ui.dialogs.MultipleContactDialog;

import java.util.List;

/**
 * Created by foc pc on 08-12-2017.
 */

public class PromoteContactAdapter extends RecyclerView.Adapter<PromoteContactAdapter.ViewHolder> {
    private List<ContactItem> contactList;
    private OnContactClickInformer onContactClickInformer;
    private Context context;

    public PromoteContactAdapter(List<ContactItem> contactList, OnContactClickInformer onContactClickInformer) {
        this.contactList = contactList;
        this.onContactClickInformer = onContactClickInformer;
        this.context = (Activity) onContactClickInformer;
    }


    @Override
    public PromoteContactAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_pick_single, parent, false);
        return new PromoteContactAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PromoteContactAdapter.ViewHolder holder, int position) {
        ContactItem contactItem = contactList.get(position);
        holder.contactNameTV.setText(contactItem.getNew_user_name());
        holder.contactNumberTV.setText(contactItem.getNew_user_phone());
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView contactNameTV;
        TextView contactNumberTV;

        public ViewHolder(View itemView) {
            super(itemView);
            contactNameTV = itemView.findViewById(R.id.contactNameTV);
            contactNumberTV = itemView.findViewById(R.id.contactNumberTV);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ContactItem contactItem = contactList.get(getAdapterPosition());
                    if (contactItem.getPhnNumbers().size()>1){
                        new MultipleContactDialog(context,contactItem.getPhnNumbers(),getAdapterPosition(),
                                contactItem.getPresentSelection(),PromoteContactAdapter.this).show();
                    } else {
                        onContactClickInformer.onContactClicked(contactList.get(getAdapterPosition()));
                    }
                }
            });


        }
    }

    interface OnContactClickInformer{
        void onContactClicked(ContactItem contactItem);
    }

    public void onContactClicked( Dialog dialog, int position,int presentSelction){
        dialog.dismiss();
        ContactItem contactItem = contactList.get(position);
        contactItem.setNew_user_phone(contactItem.getPhnNumbers().get(presentSelction));
        onContactClickInformer.onContactClicked(contactItem);
    }

}
