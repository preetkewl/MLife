package com.example.focpc.mahindralifespaces.ui.activities.promote_us;

/**
 * Created by foc pc on 14-12-2017.
 */

public interface PromoteUsView {

    void onContactPromoted();
    void onPromoteFailed(String message);
    void onSessionExpired();
    void showLoading();
    void hideLoading();
}
