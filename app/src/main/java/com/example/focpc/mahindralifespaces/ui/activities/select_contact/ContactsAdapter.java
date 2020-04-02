package com.example.focpc.mahindralifespaces.ui.activities.select_contact;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import com.mlife.R;

import com.example.focpc.mahindralifespaces.ui.dialogs.MultipleContactDialog;

import java.util.List;


public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder>     {
    private List<ContactItem> contactList;
    private ContactSelectionListener contactSelectionListener;
    private boolean isSelectionAllowed = true;
    private Context context;

    public ContactsAdapter(Context context, List<ContactItem> contactList, ContactSelectionListener contactSelectionListener) {
        this.contactList = contactList;
        this.contactSelectionListener = contactSelectionListener;
        this.context = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.select_contact_single_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ContactItem contactItem = contactList.get(position);
        holder.contactNameTV.setText(contactItem.getNew_user_name());
        holder.contactNumberTV.setText(contactItem.getNew_user_phone());
        if (contactItem.isSelected()) holder.referNowRadio.setChecked(true);
        else  holder.referNowRadio.setChecked(false);

    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

     class ViewHolder extends RecyclerView.ViewHolder {
        TextView contactNameTV;
        TextView contactNumberTV;
        RadioButton referNowRadio;

         ViewHolder(View itemView) {
            super(itemView);
            contactNameTV = itemView.findViewById(R.id.contactNameTV);
            contactNumberTV = itemView.findViewById(R.id.contactNumberTV);
            referNowRadio = itemView.findViewById(R.id.referNowRadio);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ContactItem contact = contactList.get(getAdapterPosition());
                    if (isSelectionAllowed || referNowRadio.isChecked()) {
                        if(contact.isSelected()) {
                            contact.setSelected(false);
                            referNowRadio.setChecked(!referNowRadio.isChecked());
                            contactSelectionListener.onContactSelected(referNowRadio.isChecked(), contactList.get(getAdapterPosition()));
                        } else {
                            if (contact.getPhnNumbers().size()>1){
                                new MultipleContactDialog(context,contact.getPhnNumbers(),getAdapterPosition(),
                                        contact.getPresentSelection(),ContactsAdapter.this).show();
                            }else {
                                contact.setSelected(true);
                                referNowRadio.setChecked(!referNowRadio.isChecked());
                                contactSelectionListener.onContactSelected(referNowRadio.isChecked(), contactList.get(getAdapterPosition()));
                            }
                        }
                    }else if (!isSelectionAllowed){
                        contactSelectionListener.onClickAfterMaxLimit();
                    }

                }
            });

        }
    }

    public interface ContactSelectionListener {
        void onContactSelected(boolean selected, ContactItem referUserItem);
        void onClickAfterMaxLimit();
    }

    public void onMaximumLimitOfContacts(boolean enable) {
        isSelectionAllowed = enable;
    }




    public void onContactClicked(String number, Dialog dialog, int position, int presentSelection) {
        contactList.get(position).setSelected(true);
        contactSelectionListener.onContactSelected(true, contactList.get(position));
        contactList.get(position).setPresentSelection(presentSelection);
        contactList.get(position).setNew_user_phone(number);
        notifyDataSetChanged();
        dialog.cancel();
    }

}
