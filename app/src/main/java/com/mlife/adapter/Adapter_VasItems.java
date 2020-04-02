package com.mlife.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.mlife.R;
import com.mlife.web.model.LoadVASSettingsName;
import java.util.ArrayList;
import java.util.List;

public class Adapter_VasItems extends RecyclerView.Adapter<Adapter_VasItems.MyViewHolder> {

    Context context;
    private List<LoadVASSettingsName> list;
    private List<String> vasItemsList = new ArrayList<>();
    public String vasItems = "";

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView Name;
        public CheckBox checkBox;

        public MyViewHolder(View view) {
            super(view);
            Name = (TextView) view.findViewById(R.id.tv_VasItems);
            checkBox = (CheckBox) view.findViewById(R.id.cb_VasItems);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onBindViewHolder(final Adapter_VasItems.MyViewHolder holder, final int position) {
        final LoadVASSettingsName detail = list.get(position);
        holder.Name.setText(detail.getValue());
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (vasItemsList.contains(holder.Name.getText().toString())) {
                    vasItemsList.remove(holder.Name.getText().toString());
                    vasItems = "";
                    for (String s : vasItemsList){
                        vasItems = s + "*_*" + vasItems ;
                    }

                } else {
                    vasItemsList.add(holder.Name.getText().toString());
                    vasItems = "";
                    for (String s : vasItemsList){
                        vasItems = s + "*_*" + vasItems ;
                    }
                }
            }
        });
    }

    @Override
    public Adapter_VasItems.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_items, parent, false);
        return new Adapter_VasItems.MyViewHolder(itemView);
    }

    public Adapter_VasItems(List<LoadVASSettingsName> DataList, FragmentActivity activity) {
        this.list = DataList;
        context = activity;
    }


}