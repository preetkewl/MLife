package com.example.focpc.mahindralifespaces.ui.activities.splash;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;

import com.mlife.R;


public class SplashActivity extends AppCompatActivity {
    ImageView onePercentImg;
    ConstraintLayout rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        onePercentImg = findViewById(R.id.onePercentImg);
        rootView = findViewById(R.id.rootView);
        scaleView(onePercentImg);

        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SplashActivity.this.finish();
                SplashActivity.this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out_n_translate);
            }
        });

    }

    private void scaleView(View v){
        PropertyValuesHolder scalex = PropertyValuesHolder.ofFloat(View.SCALE_X, 1.2f);
        PropertyValuesHolder scaley = PropertyValuesHolder.ofFloat(View.SCALE_Y, 1.2f);
        ObjectAnimator anim = ObjectAnimator.ofPropertyValuesHolder(v, scalex, scaley);
        anim.setRepeatCount(1);
        anim.setInterpolator(new OvershootInterpolator());
        anim.setRepeatMode(ValueAnimator.REVERSE);
        anim.setStartDelay(1000);
        anim.setDuration(1500);
        anim.start();

        anim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
               new Handler().postDelayed(new Runnable() {
                   @Override
                   public void run() {
                       SplashActivity.this.finish();
                       SplashActivity.this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out_n_translate);}
               },1000);

            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
    }

}