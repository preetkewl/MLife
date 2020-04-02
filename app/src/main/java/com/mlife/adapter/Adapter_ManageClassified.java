package com.mlife.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.mlife.R;
import com.mlife.utils.MahindraClappPreference;
import com.mlife.utils.DialogProgressBar;
import com.mlife.web.api.Service;
import com.mlife.web.holder.DataHolder;
import com.mlife.web.holder.Response.ObjectRemoveOffer;
import com.mlife.web.model.OfferListData;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by milagro on 2/22/2018.
 */

public class Adapter_ManageClassified extends RecyclerView.Adapter<Adapter_ManageClassified.MyViewHolder> implements Observer, DataHolder {

    Context context;
    private List<OfferListData> list;
    DialogProgressBar progressBar = new DialogProgressBar();
    MahindraClappPreference mahindraClappPreference;

    @Override
    public Adapter_ManageClassified.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_manage_classified, parent, false);
        objectRemoveOffer.addObserver(this);
        return new Adapter_ManageClassified.MyViewHolder(itemView);
    }

    public Adapter_ManageClassified(List<OfferListData> DataList, FragmentActivity activity) {
        this.list = DataList;
        context = activity;
        mahindraClappPreference = MahindraClappPreference.getInstance(context);
    }

    @Override
    public void update(Observable observable, Object o) {
        if (observable instanceof ObjectRemoveOffer) {
            if (objectRemoveOffer.getRemoveOfferResponse().getSuccess()) {
                progressBar.hideProgressBar();
                Toast.makeText(context, objectRemoveOffer.getRemoveOfferResponse().getMessage(), Toast.LENGTH_SHORT).show();
                objectRemoveOffer.notifyObservers();
                ((Activity) context).finish();
            }
        }
        objectRemoveOffer.deleteObservers();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_Title, tv_Detail, tv_Date;
        Button btn_Delete;

        public MyViewHolder(View view) {
            super(view);
            tv_Title = (TextView) view.findViewById(R.id.tv_Heading_ManageClassified);
            tv_Detail = (TextView) view.findViewById(R.id.tv_Details_ManageClassified);
            tv_Date = (TextView) view.findViewById(R.id.tv_postedDate_ManageClassified);
            btn_Delete = (Button) view.findViewById(R.id.btn_postedDelete_ManageClassified);
        }
    }

    @Override
    public void onBindViewHolder(final Adapter_ManageClassified.MyViewHolder holder, final int position) {
        final OfferListData detail = list.get(position);
        holder.tv_Title.setText(detail.getOfferTitle());
        holder.tv_Detail.setText(detail.getOfferDec());
        holder.tv_Date.setText(detail.getDate());

        holder.btn_Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.showProgressBar((Activity) context);
                new Service().removeOffer(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android", detail.getOfferId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}