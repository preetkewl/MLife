package com.mlife.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mlife.R;

/**
 * Created by milagro on 9/5/2017.
 */

public class InteriorAdapter extends RecyclerView.Adapter<InteriorAdapter.MyViewHolder> {

    Context context;
    private java.util.List<InteriorGetterSetter> List;

    @Override
    public InteriorAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_interior_furniture, parent, false);
        return new InteriorAdapter.MyViewHolder(itemView);
    }

    public InteriorAdapter(java.util.List<InteriorGetterSetter> DataList, FragmentActivity activity) {
        this.List = DataList;
        context = activity;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView Name, Detail, Address, Phone, Email;

        public MyViewHolder(View view) {
            super(view);
            Name = (TextView) view.findViewById(R.id.tv_Name_IF);
            Detail = (TextView) view.findViewById(R.id.tv_Details_IF);
            Address = (TextView) view.findViewById(R.id.tv_Address_IF);
            Phone = (TextView) view.findViewById(R.id.tv_Phone_IF);
            Email = (TextView) view.findViewById(R.id.tv_Email_IF);

        }
    }

    @Override
    public void onBindViewHolder(final InteriorAdapter.MyViewHolder holder, final int position) {
        final InteriorGetterSetter detail = List.get(position);

        holder.Name.setText(detail.getName());
        holder.Detail.setText(detail.getDetail());
        holder.Address.setText(detail.getAddress());
        holder.Phone.setText("Number: " + detail.getPhone());
        holder.Email.setText("Email: " + detail.getEmail());

    }

    @Override
    public int getItemCount() {
        return List.size();
    }

}