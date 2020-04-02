package com.example.focpc.mahindralifespaces.ui.activities.referal_section;

/**
 * Created by foc pc on 14-12-2017.
 */

public interface ReferSectionView {
    void onUserAdded(UserItem userItem);
    void onUserAdditionFailed(String message);
    void onSessionExpired();
    void showLoading();
    void hideLoading();
}
