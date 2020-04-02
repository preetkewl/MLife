package com.example.focpc.mahindralifespaces.ui.activities.refer_now;

import com.example.focpc.mahindralifespaces.utils.BaseListener;

import java.util.List;

/**
 * Created by foc pc on 15-12-2017.
 */

public interface ReferNowListener extends BaseListener {

    void onReferalDataFetched(List<ReferalItem> referalItems, List<LeadItem> leadItems,boolean isFromLocal);
    void onError(String error);
    void onNoDataInLocal();
}
