package com.example.focpc.mahindralifespaces.ui.activities.promote_us;

import com.example.focpc.mahindralifespaces.utils.BaseListener;

/**
 * Created by foc pc on 14-12-2017.
 */

public interface PromoteUsListener extends BaseListener {

    void onPromoted();
    void onFailedToPromote(String message);
}
