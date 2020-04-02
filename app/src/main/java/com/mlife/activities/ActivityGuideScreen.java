package com.mlife.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.mlife.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActivityGuideScreen extends AppCompatActivity {

    @BindView(R.id.ivGuide)
    ImageView ivGuide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity__guide_screen);
        ButterKnife.bind(this);


//        ActionBar actionBar = getSupportActionBar();
//        actionBar.hide();
        ivGuide.setImageResource(getIntent().getIntExtra("url",R.mipmap.postsales));
        ivGuide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityGuideScreen.this.finish();
            }
        });
    }
}
