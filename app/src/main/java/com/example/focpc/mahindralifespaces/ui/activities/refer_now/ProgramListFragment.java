package com.example.focpc.mahindralifespaces.ui.activities.refer_now;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.mlife.R;
import com.example.focpc.mahindralifespaces.ui.activities.select_contact.SelectContactActivity;
import com.example.focpc.mahindralifespaces.utils.MlsConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProgramListFragment extends Fragment implements ReferalProgramsAdapter.ReferProgramSelectListener {
    private RecyclerView programsRV;
    private Button continueBtn;
    private ReferalProgramsAdapter referalProgramsAdapter;
    private List<ReferalItem> referalItems = new ArrayList<>();
    private ReferalItem referalItem;
    private TextView emptyView;


    public ProgramListFragment() {
        // Required empty public constructor
    }

    public static ProgramListFragment newInstance() {
        return new ProgramListFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_program_list, container, false);
        programsRV = v.findViewById(R.id.programsRV);
        continueBtn = v.findViewById(R.id.continueBtn);
        emptyView = v.findViewById(R.id.emptyView);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initRV();

        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String referalUserId = "0";
                if (referalItem.getReferral_ex_users() != null && !referalItem.getReferral_ex_users().isEmpty()) {
                    referalUserId = String.valueOf(referalItem.getReferral_ex_users().get(0).getReferral_user_id());
                }
                Intent intent = new Intent(getActivity(), SelectContactActivity.class);
                intent.putExtra(MlsConstants.REFERAL_ID, String.valueOf(referalItem.getReferralId()));
                intent.putExtra(MlsConstants.REFERAL_PRJCT_NAME, String.valueOf(referalItem.getReferral_name()));
                intent.putExtra(MlsConstants.REFERAL_USER_ID, referalUserId);
                startActivityForResult(intent, MlsConstants.REFER_NOW_REQUEST);
            }
        });


    }

    private void initRV() {
        referalProgramsAdapter = new ReferalProgramsAdapter(referalItems, this, getActivity());
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        programsRV.setLayoutManager(manager);
        programsRV.setAdapter(referalProgramsAdapter);

    }

    @Override
    public void onProgramSelected(ReferalItem referalItem) {
        this.referalItem = referalItem;
        if (referalItem != null) continueBtn.setVisibility(View.VISIBLE);
        else continueBtn.setVisibility(View.GONE);
    }

    public void setData(List<ReferalItem> referalItemsList, boolean isFromLocal) {
        if (isFromLocal && referalItems != null && !referalItems.isEmpty()) return;
        if (referalItems != null && referalProgramsAdapter != null) {
            referalItem = null;
            continueBtn.setVisibility(View.GONE);
            referalItems.clear();
            referalItems.addAll(referalItemsList);
            referalProgramsAdapter.setLastCheckToNull();
        }

        if (emptyView != null) {
            if (referalItemsList.size() > 0) emptyView.setVisibility(View.GONE);
            else emptyView.setVisibility(View.VISIBLE);
        }


    }
}
