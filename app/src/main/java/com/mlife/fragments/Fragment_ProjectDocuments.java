package com.mlife.fragments;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mlife.activities.ActivityLoginWithOTP;
import com.mlife.activities.ActivityMyRequestNewTicket;
import com.mlife.activities.ActivityLogin;
import com.mlife.utils.MahindraClappPreference;
import com.mlife.utils.DialogProgressBar;
import com.mlife.web.api.Service;
import com.mlife.web.holder.DataHolder;
import com.mlife.web.holder.Response.ObjectGetDownloadDocuments;
import com.mlife.web.holder.Response.ObjectLoadDocuments;
import com.mlife.web.model.GetDownloadDocumentsData;
import com.mlife.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.mlife.utils.Constants.postSales;

public class Fragment_ProjectDocuments extends Fragment implements DataHolder, Observer {

    View view;
    DocsAdapter adapter;
    List<DocTitle> list = new ArrayList<>();
    public static String userType;
    DialogProgressBar progressBar = new DialogProgressBar();
    MahindraClappPreference mahindraClappPreference;
    private static final int REQUEST_CODE_PERMISSION = 001;
    String[] mPermission = {Manifest.permission.WRITE_EXTERNAL_STORAGE};

    @BindView(R.id.rv_docs)
    RecyclerView recyclerView;

    @BindView(R.id.tv_NoData)
    TextView tv_NoData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fragment__project_documents, container, false);
        ButterKnife.bind(this, view);
        mahindraClappPreference = MahindraClappPreference.getInstance(getContext());

        objectLoadDocuments.addObserver(this);
        objectGetDownloadDocuments.addObserver(this);
        progressBar.showProgressBar(getActivity());
        userType = mahindraClappPreference.getData("UserType");

        new Service().getDownloadDocuments(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android", mahindraClappPreference.getData("bID"));
        return view;
    }

    public class DocsAdapter extends RecyclerView.Adapter<DocsAdapter.MyViewHolder> {

        Context context;
        private java.util.List<GetDownloadDocumentsData> List;

        @Override
        public DocsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_docs, parent, false);
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
            return new DocsAdapter.MyViewHolder(itemView);
        }

        public DocsAdapter(java.util.List<GetDownloadDocumentsData> DataList, FragmentActivity activity) {
            this.List = DataList;
            context = activity;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView Title;
            public ImageView Image;


            public MyViewHolder(View view) {
                super(view);
                Title = (TextView) view.findViewById(R.id.tvHeading);
                Image = (ImageView) view.findViewById(R.id.iv_downloadDocument);
            }
        }

        @Override
        public void onBindViewHolder(final DocsAdapter.MyViewHolder holder, final int position) {
            final GetDownloadDocumentsData detail = List.get(position);
            holder.Title.setText(detail.getName());
            holder.Title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new Service().loadDocuments(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android", detail.getDType(), detail.getDocumentId(), context);
                }
            });
            holder.Image.setOnClickListener(new View.OnClickListener() {
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

    public class DocTitle {
        String Name;

        public DocTitle(String name) {
            Name = name;
        }

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }

    }

    @SuppressLint("WrongConstant")
    private boolean checkWriteExternalPermission() {
        int res = 1;
        if (getContext() != null) {
            String permission = "android.permission.WRITE_EXTERNAL_STORAGE";
            res = getContext().checkCallingOrSelfPermission(permission);
        }
        return (res == PackageManager.PERMISSION_GRANTED);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_PERMISSION) {
//            if (grantResults.length == 1 && grantResults[0] == MockPackageManager.PERMISSION_GRANTED) {
//
//            }
        }
    }

    @OnClick(R.id.fab)
    public void onClickDrop() {
        if (userType.equals(postSales)) {
            startActivity(new Intent(getContext(), ActivityMyRequestNewTicket.class).putExtra("type", "1"));
        } else {
            startActivity(new Intent(getContext(), ActivityMyRequestNewTicket.class).putExtra("type", "2"));
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        try {
            if (o instanceof ObjectGetDownloadDocuments) {
                progressBar.hideProgressBar();

                if (objectGetDownloadDocuments.getGetDownloadDocumentsResponse().getSuccess()) {
                    if (objectGetDownloadDocuments.getGetDownloadDocumentsResponse().getData().size() == 0) {
                        tv_NoData.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.GONE);
                    } else {
                        tv_NoData.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                        adapter = new DocsAdapter(objectGetDownloadDocuments.getGetDownloadDocumentsResponse().getData(), getActivity());
                        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this.getActivity());
                        recyclerView.setLayoutManager(mLayoutManager);
                        recyclerView.setItemAnimator(new DefaultItemAnimator());
                        recyclerView.setAdapter(adapter);
                    }
                } else {
                    if (objectGetDownloadDocuments.getGetDownloadDocumentsResponse().getAction().toLowerCase().equals("showlogin")) {
                        Toast.makeText(getContext(), objectGetDownloadDocuments.getGetDownloadDocumentsResponse().getMessage(), Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getActivity(), ActivityLoginWithOTP.class));
                        mahindraClappPreference.clearData();
                        getActivity().finish();
                    }
                }
            } else if (o instanceof ObjectLoadDocuments) {
                if (checkWriteExternalPermission()) {
                    if (objectLoadDocuments.getLoadDocumentsResponse().getSuccess()) {
                        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
                        currentDateTimeString.replaceAll(" ", "_");
                        final File dwldsPath = new File(Environment.getExternalStorageDirectory() + File.separator + "Mahindra_" + currentDateTimeString + ".pdf");
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
                        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                        startActivity(intent);
                    }
                } else {
                    Toast.makeText(getContext(), "- Please Allow Access To Storage -", Toast.LENGTH_SHORT).show();
                    ActivityCompat.requestPermissions(getActivity(), mPermission, REQUEST_CODE_PERMISSION);
                }
            }
        } catch (Exception ex) {}
    }

}