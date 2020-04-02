package com.example.focpc.mahindralifespaces.ui.activities.select_contact;

import android.content.Context;

import java.util.List;

/**
 * Created by foc pc on 06-12-2017.
 */

public interface SelectContactInteractor {

    void checkIfAnyUserEligible(List<ContactItem> contactItemList,SelectContactListener listener,
                                String referalId,String referralUserId);
    void referUsers(List<ContactItem> contactItemList, String referalId, String comment,
                    SelectContactListener listener,String referralUserId);

}
