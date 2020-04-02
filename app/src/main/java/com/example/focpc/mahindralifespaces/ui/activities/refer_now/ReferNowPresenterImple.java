package com.example.focpc.mahindralifespaces.ui.activities.refer_now;

import android.content.Context;
import android.os.AsyncTask;

import java.util.Collections;
import java.util.List;

/**
 * Created by foc pc on 15-12-2017.
 */

public class ReferNowPresenterImple implements ReferNowPresenter, ReferNowListener {

    private ReferNowView referNowView;
    private ReferNowInteractor referNowInteractor;

    public ReferNowPresenterImple(ReferNowView referNowView) {
        this.referNowView = referNowView;
        referNowInteractor = new ReferNowInteractorImple();
    }

    @Override
    public void getReferaDataFromLocal() {
        referNowView.showLoading();
        new ReferalDataFetchAsync((ReferNowActivity) referNowView,this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override
    public void getReferalData(Context context,String userId) {
        referNowInteractor.getReferalData(userId,context, this);
    }


    @Override
    public void onSessionExpired() {
        referNowView.hideLoading();
        referNowView.onSessionExpired();
    }

    @Override
    public void onApiError(String message) {
        onError(message);
    }

    @Override
    public void onReferalDataFetched(List<ReferalItem> referalItems, List<LeadItem> leadItems,boolean isFromLocal) {
        referNowView.hideLoading();
        referNowView.onReferalDataFetched(referalItems, leadItems,isFromLocal);
    }

    @Override
    public void onError(String error) {
        referNowView.hideLoading();
        referNowView.onFailure(error);
    }

    @Override
    public void onNoDataInLocal() {
        referNowView.onNoDataInLocal();
    }
}
