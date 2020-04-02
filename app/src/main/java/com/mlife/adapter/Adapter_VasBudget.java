package com.mlife.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.mlife.R;
import com.mlife.web.model.LoadVASSettingsBudget;

import java.util.List;

/**
 * Created by milagro on 1/10/2018.
 */

public class Adapter_VasBudget extends RecyclerView.Adapter<Adapter_VasBudget.MyViewHolder> {

    Context context;
    RadioButton button;
    public String vasBudget = "";
    private List<LoadVASSettingsBudget> list;

    @Override
    public Adapter_VasBudget.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_budget, parent, false);
        return new Adapter_VasBudget.MyViewHolder(itemView);
    }

    public Adapter_VasBudget(List<LoadVASSettingsBudget> DataList, FragmentActivity activity) {
        this.list = DataList;
        context = activity;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView Name;
        public RadioButton radioButton;

        public MyViewHolder(View view) {
            super(view);
            Name = (TextView) view.findViewById(R.id.tv_VasItems);
            radioButton = (RadioButton) view.findViewById(R.id.rb_VasItems);
        }
    }

    @Override
    public void onBindViewHolder(final Adapter_VasBudget.MyViewHolder holder, final int position) {
        final LoadVASSettingsBudget detail = list.get(position);

        holder.Name.setText(detail.getValue());
        holder.radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (button == null) {
                    button = holder.radioButton;
                    button.setChecked(true);
                    vasBudget = holder.Name.getText().toString();
                    Toast.makeText(context, "Budget range: " + vasBudget, Toast.LENGTH_SHORT).show();
                } else {
                    button.setChecked(false);
                    button = holder.radioButton;
                    button.setChecked(true);
                    vasBudget = holder.Name.getText().toString();
                    Toast.makeText(context, "Budget range: " + vasBudget, Toast.LENGTH_SHORT).show();
                }
            }
        });

        holder.radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}