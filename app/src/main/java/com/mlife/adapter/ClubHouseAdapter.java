package com.mlife.adapter;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mlife.utils.DialogProgressBar;
import com.mlife.R;
import com.mlife.utils.MahindraClappPreference;
import com.mlife.web.holder.DataHolder;
import com.mlife.web.model.ClubHouseListData;

public class ClubHouseAdapter extends RecyclerView.Adapter<ClubHouseAdapter.MyViewHolder> implements DataHolder {

    FragmentActivity context;
    DialogProgressBar progressBar = new DialogProgressBar();
    private java.util.List<ClubHouseListData> List;
    MahindraClappPreference mahindraClappPreference;

    ClubHouseListData canceledClickDate;
    OnItemclick onItemclick;





    @Override
    public ClubHouseAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_clubhouse, parent, false);
        mahindraClappPreference = MahindraClappPreference.getInstance(context);
       //
        return new ClubHouseAdapter.MyViewHolder(itemView);
    }

    public ClubHouseAdapter(java.util.List<ClubHouseListData> DataList, FragmentActivity activity,OnItemclick onItemclick) {
        this.List = DataList;
        context = activity;
        this.onItemclick = onItemclick;
    }



    public interface OnItemclick{

         void onItemClick(View v,ClubHouseListData detail);
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView Name, Date, Time, Count, Status;
        public ImageView iv_Status;
//        public LinearLayout btn_CancleClubEvent;
        Button btn_Cancle;

        public MyViewHolder(View view) {
            super(view);
            Name = (TextView) view.findViewById(R.id.tv_ClubEventName);
            Date = (TextView) view.findViewById(R.id.tv_ClubEventBookingDate);
            Time = (TextView) view.findViewById(R.id.tv_ClubEventTimeSlot);
            Count = (TextView) view.findViewById(R.id.tv_ClubEventPeopleNumber);
            Status = (TextView) view.findViewById(R.id.tv_ClubEventStatus);
            iv_Status = (ImageView) view.findViewById(R.id.iv_ClubEventStatus);
//            btn_CancleClubEvent = (LinearLayout) view.findViewById(R.id.btn_CancleClubEvent);
            btn_Cancle = (Button)view.findViewById(R.id.btn_Cancle);
        }
    }

    @Override
    public void onBindViewHolder(final ClubHouseAdapter.MyViewHolder holder, final int position) {
        final ClubHouseListData detail = List.get(position);


        holder.Name.setText(detail.getClubhouseBookingTitle());
        holder.Date.setText("Booking Date: " + detail.getClubhouseBookingDate());
        holder.Time.setText("Time Slot: " + detail.getClubhouseBookingTimeslot());
        holder.Count.setText("Number of People: " + detail.getClubhouseBookingNoofpeople());

        if (detail.getClubhouseBooked().equals("1")) {
            holder.Status.setText("Booked");
        } else {
            holder.Status.setText("Cancelled");
        }

        if (detail.getClubhouseBooked().equals("1")) {
            holder.iv_Status.setImageResource(R.mipmap.bookclubhouse);
            holder.btn_Cancle.setVisibility(View.VISIBLE);
        } else {
            holder.iv_Status.setImageResource(R.mipmap.cancelledclubhouse);
            holder.btn_Cancle.setVisibility(View.GONE);
        }

        holder.btn_Cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemclick.onItemClick(v,detail);

            }
        });
    }

    @Override
    public int getItemCount() {
        return List.size();
    }

}