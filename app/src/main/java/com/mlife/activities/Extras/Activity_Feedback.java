package com.mlife.activities.Extras;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.mlife.web.api.Service;
import com.mlife.R;
import com.mlife.utils.MahindraClappPreference;
import com.mlife.web.holder.DataHolder;

import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Activity_Feedback extends AppCompatActivity implements Observer, DataHolder {

    Intent intent;
    String str_rating = "";
    MahindraClappPreference mahindraClappPreference;

    @BindView(R.id.tv_Title)
    TextView tv_Title;

    @BindView(R.id.tv_Rating)
    TextView tv_Rating;

    @BindView(R.id.rb_Rating)
    RatingBar rb_Rating;

    @BindView(R.id.et_Feedback)
    EditText et_Feedback;

    @OnClick(R.id.btn_Submit)
    public void submitFeedback() {
        new Service().submitFeedback(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android", et_Feedback.getText().toString(), str_rating, intent.getStringExtra("type"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__feedback);
        ButterKnife.bind(this);
        mahindraClappPreference = MahindraClappPreference.getInstance(getApplicationContext());
        intent = getIntent();
        rb_Rating.setMax(5);
        tv_Title.setText(intent.getStringExtra("title"));
        objectSubmitFeedback.addObserver(this);

        rb_Rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if (rating == 0) {
                    rb_Rating.setRating(1);
                }

                if (rating <= 1) {
                    tv_Rating.setText("Poor");
                } else if (rating <= 2) {
                    tv_Rating.setText("Average");
                } else if (rating <= 3) {
                    tv_Rating.setText("Fair");
                } else if (rating <= 4) {
                    tv_Rating.setText("Good");
                } else if (rating <= 5) {
                    tv_Rating.setText("Excellent");
                }

                str_rating = String.valueOf(rating);

            }
        });
    }

    @Override
    public void update(Observable o, Object arg) {
        if (objectSubmitFeedback.getSubmitFeedbackResponse().getSuccess()) {
            startActivity(new Intent(this, Activity_ThankYou.class).putExtra("Title", "Feedback").putExtra("Heading", "Thank You").putExtra("Detail", "Your response has been received.").putExtra("Image", R.mipmap.thankyouiconclubhouse).putExtra("callBack", "Feedback"));
       finish();
        }else {
            Toast.makeText(this, objectSubmitFeedback.getSubmitFeedbackResponse().getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private Activity_Feedback.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final Activity_Feedback.ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        }
    }

    @OnClick(R.id.iv_Back_ManageDitePlan)
    public void backClick() {
        finish();
    }

}