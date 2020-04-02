package com.mlife.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.StrictMode;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.*;

import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mlife.web.api.Service;
import com.mlife.R;
import com.mlife.utils.MahindraClappPreference;
import com.mlife.utils.DialogProgressBar;
import com.mlife.web.holder.DataHolder;
import com.mlife.web.model.GetPaymentDetailsDetails;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by milagro on 9/11/2017.
 */

public class Payment_Adapter extends RecyclerView.Adapter<Payment_Adapter.MyViewHolder> implements DataHolder, Observer {

    Context context;
    DialogProgressBar progressBar;
    OnclickListener onclickListener;
    private List<GetPaymentDetailsDetails> List;
    MahindraClappPreference mahindraClappPreference;

    @Override
    public Payment_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_payments, parent, false);
        mahindraClappPreference = MahindraClappPreference.getInstance(context);
        objectLoadDocuments.addObserver(this);

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        return new Payment_Adapter.MyViewHolder(itemView);
    }

    public Payment_Adapter(java.util.List<GetPaymentDetailsDetails> DataList, FragmentActivity activity, OnclickListener onclickListener) {
        this.List = DataList;
        context = activity;
        progressBar = new DialogProgressBar();
        this.onclickListener = onclickListener;
    }

    @Override
    public void update(Observable o, Object arg) {

        if (objectLoadDocuments.getLoadDocumentsResponse().getSuccess()) {
            String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
            currentDateTimeString = currentDateTimeString.replaceAll(" ", "_");
            final File dwldsPath = new File(Environment.getExternalStorageDirectory() + File.separator + "Mahindra_Payment_" + currentDateTimeString + ".pdf");
            try {
                dwldsPath.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            byte[] pdfAsBytes = Base64.decode(objectLoadDocuments.getLoadDocumentsResponse().getData(), Base64.DEFAULT);
            FileOutputStream os;
            try {
                os = new FileOutputStream(dwldsPath, true);
                os.write(pdfAsBytes);
                os.flush();
                os.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.fromFile(dwldsPath), "application/pdf");
            context.startActivity(intent);
        }else {
            Toast.makeText(context, objectLoadDocuments.getLoadDocumentsResponse().getMessage(), Toast.LENGTH_SHORT).show();
        }
        objectLoadComments.deleteObservers();
    }

    public interface OnclickListener {
        void btnOnclick(GetPaymentDetailsDetails detail);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvInvoiceNumber, tvMonth, tvAmount, tvDueDate, tvDescription, tv_DownloadIvoice;
        public LinearLayout llpayment;
        Button btn_Pay,btn_DemandLetter;
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
            btn_Pay = (Button) view.findViewById(R.id.btn_Pay);
            btn_DemandLetter = (Button)view.findViewById(R.id.btn_DemandLetter);
            llpayment = (LinearLayout) view.findViewById(R.id.llpayment);
        }

        public void bind(final GetPaymentDetailsDetails detail, final Button btn_pay) {
            btn_pay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onclickListener.btnOnclick(detail);
                }
            });
        }
    }

    @Override
    public void onBindViewHolder(final Payment_Adapter.MyViewHolder holder, final int position) {
        final GetPaymentDetailsDetails detail = List.get(position);

        holder.tvInvoiceNumber.setText("Invoice No. " + detail.getRef());
        holder.tvMonth.setText("Month: " + detail.getDate());
        holder.tvDescription.setText("Description: " + detail.getDescription());
        holder.bind(detail, holder.btn_Pay);

        if (detail.getPaid() == 0) {
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

        holder.btn_DemandLetter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Service().loadDocuments(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android", detail.getDType(), detail.getDocumentId(), context);
            }
        });
        holder.tv_DownloadIvoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Service().loadDocuments(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android", detail.getDType(), detail.getDocumentId(), context);
            }
        });
    }

    @Override
    public int getItemCount() {
        return List.size();
    }

}