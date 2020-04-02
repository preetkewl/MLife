package com.example.focpc.mahindralifespaces.ui.activities.select_contact;

import android.app.Dialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import com.mlife.R;


import java.util.List;


/**
 * Created by user on 03-11-2017.
 */

public class MultiContactAdapter extends RecyclerView.Adapter<MultiContactAdapter.CntactVH> {

    private List<String> numbers;
    private int presentSelection;

    public MultiContactAdapter(List<String> numbers , int presentSelection) {
        this.numbers = numbers;
        this.presentSelection = presentSelection;
    }


    @Override
    public CntactVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.multi_contact_single,parent,false);
        return new CntactVH(v);
    }

    @Override
    public void onBindViewHolder(CntactVH holder, int position) {
        holder.cntactTV.setText(numbers.get(position));
        holder.contact_radio.setChecked(position == presentSelection);



    }

    @Override
    public int getItemCount() {
        return numbers.size();
    }

    class CntactVH extends RecyclerView.ViewHolder{
        TextView cntactTV;
        RadioButton contact_radio;
        CntactVH(View itemView) {
            super(itemView);
            cntactTV = itemView.findViewById(R.id.cntactTV);
            contact_radio = itemView.findViewById(R.id.contact_radio);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (contact_radio.isChecked()) {
                        contact_radio.setChecked(false);
                        presentSelection = -1;
                        notifyItemChanged(getAdapterPosition());
                    } else {
                        contact_radio.setChecked(true);
                        presentSelection = getAdapterPosition();
                        notifyDataSetChanged();
                    }


                }
            });
        }
    }

    public int getPresentPosition(){
        return presentSelection;
    }
}
