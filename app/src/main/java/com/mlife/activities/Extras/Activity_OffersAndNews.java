package com.mlife.activities.Extras;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mlife.activities.ActivityHome;
import com.mlife.activities.ActivityNotification;
import com.mlife.adapter.Offers_Adapter;
import com.mlife.adapter.OffersGetterSetter;
import com.mlife.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Activity_OffersAndNews extends AppCompatActivity {

    @BindView(R.id.rv_OffersAndNews)
    RecyclerView recyclerView;

    Offers_Adapter offers_adapter;
    List<OffersGetterSetter> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offers_and_news);
        ButterKnife.bind(this);
//        initilize();
    }

//    private void initilize() {
//
//        OffersGetterSetter getterSetter = new OffersGetterSetter(R.mipmap.news1, "Mahindra", "Noida, Sector-7(Huda City Center)", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.\n\n It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.");
//        list.add(getterSetter);
//
//        getterSetter = new OffersGetterSetter(R.mipmap.news2, "Tata Homes", "Goa, Near Anjana Beach", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.\n\n It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.");
//        list.add(getterSetter);
//
//        getterSetter = new OffersGetterSetter(R.mipmap.news3, "Hero Dream City", "Mohali, Sector-117, Landran Highway", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.\n\n It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.");
//        list.add(getterSetter);
//
//        getterSetter = new OffersGetterSetter(R.mipmap.news1, "SBP Groups", "Banglore", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.\n\n It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.");
//        list.add(getterSetter);
//
//        offers_adapter = new Offers_Adapter(list, Activity_OffersAndNews.this);
//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
//        recyclerView.setLayoutManager(mLayoutManager);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.setAdapter(offers_adapter);
//
//    }

    @OnClick(R.id.iv_Back_ManageDitePlan)
    public void backClick(){
        finish();
    }

    @OnClick(R.id.iv_Notifications)
    public void notificationClick(View view){
        startActivity(new Intent(getApplicationContext(), ActivityNotification.class));
    }

    @OnClick(R.id.iv_MahindraLogo)
    public void logoClick(){
        startActivity(new Intent(getApplicationContext(), ActivityHome.class));
    }


}
