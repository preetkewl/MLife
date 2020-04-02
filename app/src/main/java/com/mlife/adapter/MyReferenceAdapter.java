package com.mlife.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;

import java.util.Locale;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.mlife.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by milagro on 8/29/2017.
 */

public class MyReferenceAdapter extends RecyclerView.Adapter<MyReferenceAdapter.MyViewHolder> {

    Context context;
    private java.util.List<Contact> List;
    public static java.util.List<String> Contacts = new ArrayList<>();
    private List<Contact> arraylist;

    @Override
    public MyReferenceAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_referral, parent, false);
        return new MyReferenceAdapter.MyViewHolder(itemView);
    }

    public MyReferenceAdapter(java.util.List<Contact> DataList, FragmentActivity activity) {
        List = DataList;
        arraylist = new ArrayList<>(List);
        context = activity;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView Name, Tag;
        public CheckBox cb;

        public MyViewHolder(View view) {
            super(view);
            Name = (TextView) view.findViewById(R.id.tv_personName);
            Tag = (TextView) view.findViewById(R.id.tv_alreadyInvited);
            cb = (CheckBox) view.findViewById(R.id.cb_isChecked);


        }
    }

    @Override
    public void onBindViewHolder(final MyReferenceAdapter.MyViewHolder holder, final int position) {
        final Contact detail = List.get(position);

        holder.Name.setText(detail.getName());
        holder.cb.setOnCheckedChangeListener(null);
        holder.cb.setSelected(detail.isChecked());
        holder.cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    detail.setChecked(true);
                    Contacts.add(detail.getPhoneNumber());
                } else {
                    detail.setChecked(false);
                    Contacts.remove(detail.getPhoneNumber());
                }
            }
        });
        holder.cb.setChecked(detail.isChecked());
    }

    @Override
    public int getItemCount() {
        return List.size();
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        List.clear();

        if (charText.length() == 0) {
            List.addAll(arraylist);
        } else {
            for (Contact wp : arraylist) {
                if (wp.getName().toLowerCase(Locale.getDefault()).contains(charText)) {
                    List.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }
}