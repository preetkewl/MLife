package com.mlife.adapter;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.mlife.fragments.FirstFragment;
import com.mlife.utils.Constants;
import com.mlife.R;
import com.mlife.web.holder.Response.ObjectAddedValueList;
import com.mlife.web.model.AddedValueListData;


import io.fabric.sdk.android.services.concurrency.AsyncTask;

/**
 * Created by milagro on 10/9/2017.
 */

public class DocsAdapter extends RecyclerView.Adapter<DocsAdapter.MyViewHolder> {

    FirstFragment context;
    ObjectAddedValueList dataHolder;
    private java.util.List<AddedValueListData> List;

    @Override
    public DocsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_documents, parent, false);
        return new DocsAdapter.MyViewHolder(itemView);
    }

    public DocsAdapter(java.util.List<AddedValueListData> DataList, FirstFragment activity, ObjectAddedValueList addedValue) {
        this.List = DataList;
        context = activity;
        dataHolder = addedValue;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView Title, Vendor;
        public CheckBox cb_Vas;

        public MyViewHolder(View view) {
            super(view);
            Title = (TextView) view.findViewById(R.id.tvHeading);
            Vendor = (TextView) view.findViewById(R.id.tvVendor);
            cb_Vas = (CheckBox) view.findViewById(R.id.cb_Vas);
        }
    }

    public class DownloadInBackground extends AsyncTask <Integer,Void,Void>{

        AddedValueListData addedValueListData;


        @Override
        protected Void doInBackground(Integer... integers) {
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }

    @Override
    public void onBindViewHolder(final DocsAdapter.MyViewHolder holder, final int position) {
        final AddedValueListData detail = List.get(position);
        holder.Title.setText(detail.getValueAddedserviceTitle());
        holder.Vendor.setText(detail.getValueAddedserviceVender());
        holder.Title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    new Thread(new Runnable() {
                        public void run() {
                            AddedValueListData addedValueListData = dataHolder.getAddedValueListData().getData().get(position);
                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(Constants.pdf + addedValueListData.getValueAddedserviceAttachment()));
                            context.startActivity(browserIntent);
                        }
                    }).start();

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(context.getContext(), "Something went wrong, Try again later", Toast.LENGTH_SHORT).show();
                }
            }
        });

        holder.Vendor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    AddedValueListData addedValueListData = dataHolder.getAddedValueListData().getData().get(position);
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(Constants.pdf + addedValueListData.getValueAddedserviceAttachment()));
                    context.startActivity(browserIntent);
                } catch (Exception e) {
                    Toast.makeText(context.getContext(), "Something went wrong, Try again later", Toast.LENGTH_SHORT).show();
                }
            }
        });

        holder.cb_Vas.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    context.vasId.add(detail.getValueAddedserviceId());
                } else if (context.vasId.contains(detail.getValueAddedserviceId())) {
                    context.vasId.remove(detail.getValueAddedserviceId());
                }
            }
        });

        if (context.vasId.contains(detail.getValueAddedserviceId())){
            holder.cb_Vas.setChecked(true);
        }
    }

    @Override
    public int getItemCount() {
        return List.size();
    }

}