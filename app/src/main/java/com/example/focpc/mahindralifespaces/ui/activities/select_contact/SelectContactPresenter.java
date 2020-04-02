package com.example.focpc.mahindralifespaces.ui.activities.select_contact;

import android.content.Context;

import java.util.List;

/**
 * Created by foc pc on 06-12-2017.
 */

public interface SelectContactPresenter {

    void fetchContacts(Context context);
    void checkIfAnyUserEligible(List<ContactItem> contactItemList,String referalId ,String referalUserId);
    void referUsers(List<ContactItem> contactItemList,String referalId,String comment ,String referalUserId);


}
