package com.example.focpc.mahindralifespaces.ui.activities.select_contact;

import android.content.Context;

import com.example.focpc.mahindralifespaces.utils.BaseListener;

import java.util.List;

/**
 * Created by foc pc on 06-12-2017.
 */

public interface SelectContactListener extends BaseListener {

    void onContctFetched(List<ContactItem> contactItemList,List<ContactItem> contactItemListClone);
    void onContactfetchFail();
    void onNoContactsInFile();
    void userExistCheckResult(int newUserCount, int oldUserCount, int invalidUserCount,List<ContactItem> contactItemList);
    void onError(String message);
    void onReferSuccess(List<UserStatusItem> userStatusArray);
}
