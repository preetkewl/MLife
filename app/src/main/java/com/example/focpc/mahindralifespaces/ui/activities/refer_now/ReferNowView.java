package com.example.focpc.mahindralifespaces.ui.activities.refer_now;

import java.util.List;

/**
 * Created by foc pc on 15-12-2017.
 */

public interface ReferNowView {
    void onReferalDataFetched(List<ReferalItem> referalItems, List<LeadItem> leadItems,boolean isFromLocal);
    void onFailure (String message);
    void onSessionExpired();
    void showLoading();
    void hideLoading();
    void onNoDataInLocal();
}
