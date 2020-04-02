package com.mlife.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mlife.web.model.GetPostPaymentDetailsDetails;
import com.mlife.R;

/**
 * Created by milagro on 10/11/2017.
 */

public class PostPaymentAdapter  extends RecyclerView.Adapter<PostPaymentAdapter.MyViewHolder> {

    Context context;
    private java.util.List<GetPostPaymentDetailsDetails> List;
  //  Fragment fragment = new Fragment_PaymentHistory();

    @Override
    public PostPaymentAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_payments, parent, false);
        return new PostPaymentAdapter.MyViewHolder(itemView);
    }

    public PostPaymentAdapter(java.util.List<GetPostPaymentDetailsDetails> DataList, FragmentActivity activity) {
        this.List = DataList;
        context = activity;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvInvoiceNumber, tvMonth, tvAmount, tvDueDate, tvDescription, tv_DownloadIvoice;
        public LinearLayout llpayment;
        public ImageView iv_DownloadIvoice;


        public MyViewHolder(View view) {
            super(view);
            iv_DownloadIvoice = (ImageView) view.findViewById(R.id.iv_DownloadIvoice);
            tvInvoiceNumber = (TextView) view.findViewById(R.id.tv_InvoiceNumber);
            tvMonth = (TextView) view.findViewById(R.id.tv_Date);
            tvAmount = (TextView) view.findViewById(R.id.tv_Amount);
            tvDueDate = (TextView) view.findViewById(R.id.tv_DueDate);
            tvDescription = (TextView) view.findViewById(R.id.tv_Description);
            tv_DownloadIvoice = (TextView) view.findViewById(R.id.tv_DownloadIvoice);
            llpayment = (LinearLayout) view.findViewById(R.id.llpayment);
        }
    }

    @Override
    public void onBindViewHolder(final PostPaymentAdapter.MyViewHolder holder, final int position) {
        final GetPostPaymentDetailsDetails detail = List.get(position);


        holder.tvInvoiceNumber.setText("Invoice No. " + detail.getRef());
        holder.tvMonth.setText("Month: " + detail.getDate());
        holder.tvDescription.setText("Description: " + detail.getDescription());

        if (detail.getPaid().equals("0")) {
            holder.tvAmount.setText("Amount Due: " + detail.getAmount());
            holder.tvDueDate.setText("Due Date: " + detail.getDate());
            holder.tv_DownloadIvoice.setVisibility(View.GONE);
            holder.llpayment.setVisibility(View.VISIBLE);
            holder.iv_DownloadIvoice.setVisibility(View.GONE);
        } else {
            holder.tvAmount.setText("Amount Paid: " + detail.getAmount());
            holder.tvDueDate.setText("Date: " + detail.getDate());
            holder.tv_DownloadIvoice.setVisibility(View.VISIBLE);
            holder.iv_DownloadIvoice.setVisibility(View.VISIBLE);
            holder.llpayment.setVisibility(View.GONE);
        }


    }

    @Override
    public int getItemCount() {
        return List.size();
    }

}