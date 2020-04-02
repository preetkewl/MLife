package com.example.focpc.mahindralifespaces.ui.activities.select_contact;

import java.util.List;

/**
 * Created by foc pc on 06-12-2017.
 */

public interface SelectContactView {

    void onContactsFetched(List<ContactItem> contactItemList, List<ContactItem> contactItemListOriginal);

    void showLoading();

    void hideLoading();

    void onContactFetchFailed();

    void onSessionExpired();

    void onError(String error);

    void userExistCheckResult(int newUserCount, int oldUserCount, int invalidUserCount,List<ContactItem> contactItemList);

    void onReferSuccess(List<UserStatusItem> userStatusArray);

    void showLoadingDialog();
    void hideLoadingDialog();

}
