package com.example.focpc.mahindralifespaces.ui.activities.referal_section;

import com.example.focpc.mahindralifespaces.utils.BaseListener;

/**
 * Created by foc pc on 14-12-2017.
 */

public interface ReferSectionListener extends BaseListener {

    void onNewUserAdded(UserItem userItem);
    void onUserAdditionFailed(String message);

}
