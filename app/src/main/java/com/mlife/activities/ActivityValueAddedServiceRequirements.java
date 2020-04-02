package com.mlife.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mlife.activities.Extras.Activity_ThankYou;
import com.mlife.adapter.Adapter_VasBudget;
import com.mlife.adapter.Adapter_VasItems;
import com.mlife.R;
import com.mlife.utils.MahindraClappPreference;
import com.mlife.utils.DialogProgressBar;
import com.mlife.web.api.Service;
import com.mlife.web.holder.DataHolder;
import com.mlife.web.holder.Response.ObjectLoadVASSettings;
import com.mlife.web.holder.Response.ObjectSaveVASRequirements;

import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActivityValueAddedServiceRequirements extends AppCompatActivity implements DataHolder, Observer {

    String vasItems = "";
    String vasBudget = "";
    Adapter_VasItems adapter_vasItems;
    Adapter_VasBudget adapter_vasBudget;
    DialogProgressBar progressBar = new DialogProgressBar();
    MahindraClappPreference mahindraClappPreference;

    @BindView(R.id.rv_VasItems)
    RecyclerView rv_VasItems;

    @BindView(R.id.et_Description)
    EditText et_Description;

    @BindView(R.id.rv_VasBudget)
    RecyclerView rv_VasBudget;

    @BindView(R.id.btn_Submit_Forgot)
    Button btn_Submit_Forgot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__vas__requirements);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        progressBar.showProgressBar(this);
        mahindraClappPreference = MahindraClappPreference.getInstance(getApplicationContext());
        objectLoadVASSettings.addObserver(this);
        objectSaveVASRequirements.addObserver(this);
        new Service().loadVASSettings(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android");
    }

    @OnClick(R.id.btn_Submit_Forgot)
    public void submitData() {

        vasBudget = adapter_vasBudget.vasBudget;
        vasItems = adapter_vasItems.vasItems;

        if (vasItems.isEmpty()){
            Toast.makeText(this, "Please select at least one option", Toast.LENGTH_SHORT).show();
        } else if (vasBudget.isEmpty()){
            Toast.makeText(this, "Please select budget option", Toast.LENGTH_SHORT).show();
        }else {
            vasItems = vasItems.substring(0, vasItems.length() - 3);
        progressBar.showProgressBar(ActivityValueAddedServiceRequirements.this);
        new Service().saveVASRequirements(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android", vasItems, et_Description.getText().toString().trim(), vasBudget, mahindraClappPreference.getData("Project"));
        }

    }

    @Override
    public void update(Observable observable, Object o) {

        if (observable instanceof ObjectSaveVASRequirements) {

            progressBar.hideProgressBar();
            if (objectSaveVASRequirements.getSaveVASRequirementsResponse().getSuccess()) {
                observable.deleteObservers();
                startActivity(new Intent(ActivityValueAddedServiceRequirements.this, Activity_ThankYou.class).putExtra("Title", "Value added services").putExtra("Heading", "Thank You").putExtra("Detail", "Your request has been received \n our representative will contact you soon.").putExtra("Image", R.mipmap.thankyouiconclubhouse).putExtra("callBack", "Vas"));
                finish();
            } else {
                Toast.makeText(this, objectSaveVASRequirements.getSaveVASRequirementsResponse().getMessage(), Toast.LENGTH_SHORT).show();
            }

        } else if (observable instanceof ObjectLoadVASSettings) {
            progressBar.hideProgressBar();

            adapter_vasItems = new Adapter_VasItems(objectLoadVASSettings.getLoadVASSettingsResponse().getData().getNames(), this);
            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
            rv_VasItems.setLayoutManager(mLayoutManager);
            rv_VasItems.setItemAnimator(new DefaultItemAnimator());
            rv_VasItems.setAdapter(adapter_vasItems);

            adapter_vasBudget = new Adapter_VasBudget(objectLoadVASSettings.getLoadVASSettingsResponse().getData().getBudgets(), this);
            RecyclerView.LayoutManager mLayoutManager2 = new GridLayoutManager(getApplicationContext(), 2);
            rv_VasBudget.setLayoutManager(mLayoutManager2);
            rv_VasBudget.setItemAnimator(new DefaultItemAnimator());
            rv_VasBudget.setAdapter(adapter_vasBudget);
        }
    }

    @OnClick(R.id.iv_Back_ManageDitePlan)
    public void backClick() {
        finish();
    }

    @OnClick(R.id.iv_Notification)
    public void notificationClick() {
        startActivity(new Intent(getApplicationContext(), ActivityNotification.class));
    }

    @OnClick(R.id.iv_MahindraLogo)
    public void logoClick() {
        startActivity(new Intent(getApplicationContext(), ActivityHome.class));
    }

}