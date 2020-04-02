package com.example.focpc.mahindralifespaces.ui.activities.promote_us;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import com.example.focpc.mahindralifespaces.utils.BaseActivity;
import com.example.focpc.mahindralifespaces.utils.MlsConstants;
import com.mlife.R;

public class PromoteUsActivity extends BaseActivity implements View.OnClickListener {
    private String [] typeArray = {"Bloggers","Corporates","Housing Societies","Restaurants/Clubs","Schools/Colleges"};
    private CardView dropDown;
    private ImageView dropDownArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promote_us);

        dropDown = findViewById(R.id.dropDown);
        FrameLayout dropDownBtn = findViewById(R.id.dropDownBtn);
        ConstraintLayout promoteUsRoot = findViewById(R.id.promoteUsRoot);
        TextView promoteUsDesc = findViewById(R.id.promoteUsDesc);
        dropDownArrow = findViewById(R.id.dropDownArrow);
        dropDownBtn.setOnClickListener(this);
        promoteUsRoot.setOnClickListener(this);
        initListView();
        colorTV(promoteUsDesc);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.promoteUsRoot:
                if (dropDown.getVisibility() == View.VISIBLE) {
                    dropDown.setVisibility(View.GONE);
                    dropDownArrow.animate().rotation(0f).start();
                }
                break;
            case R.id.dropDownBtn:
                if (dropDown.getVisibility()==View.GONE) {
                    dropDown.setVisibility(View.VISIBLE);
                    dropDownArrow.animate().rotation(-180f).start();
                } else {
                    dropDown.setVisibility(View.GONE);
                    dropDownArrow.animate().rotation(0f).start();
                }
                break;
        }

    }

    private void initListView(){
        ListView simpleList = findViewById(R.id.simpleList);
        final TextView clickToChooseTV = findViewById(R.id.clickToChooseTV);
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.promote_us_listing, R.id.typeTV, typeArray);
        simpleList.setAdapter(adapter);
        simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                clickToChooseTV.setText(typeArray[i]);
                dropDown.setVisibility(View.GONE);
                dropDownArrow.animate().rotation(0f).start();
                Intent intent = new Intent(PromoteUsActivity.this,PromoteUsInputActivity.class);
                intent.putExtra(MlsConstants.INSTITUTION_TYPE,typeArray[i]);
                startActivity(intent);
                overridePendingTransition(R.anim.push_up_in, R.anim.fade_out);
            }
        });
    }

    private void colorTV(TextView tv){
        Spannable WordtoSpan = new SpannableString(getString(R.string.help_us_spread_word));
        WordtoSpan.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorMain)), 8, 23,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        WordtoSpan.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorMain)), 46, 49,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        WordtoSpan.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorMain)), 54, 61,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.setText(WordtoSpan);
    }
}