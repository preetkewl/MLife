package com.example.focpc.mahindralifespaces.ui.activities.select_contact;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.focpc.mahindralifespaces.utils.BaseActivity;

import java.util.List;

/**
 * Created by foc pc on 06-12-2017.
 */

public class SelectContactPresenterImple implements SelectContactPresenter, SelectContactListener {

    private SelectContactView selectContactView;
    private SelectContactInteractor selectContactInteractor;

    public SelectContactPresenterImple(SelectContactView selectContactView) {
        this.selectContactView = selectContactView;
        this.selectContactInteractor = new SelectContactInteractorImple();
    }

    @Override
    public void fetchContacts(Context context) {
        selectContactView.showLoading();
        new ReadContactAsync(context, this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override
    public void onContctFetched(List<ContactItem> contactItemList, List<ContactItem> contactItemListOriginal) {
        selectContactView.hideLoading();
        selectContactView.onContactsFetched(contactItemList, contactItemListOriginal);

    }

    @Override
    public void onContactfetchFail() {
        selectContactView.hideLoading();
        selectContactView.onContactFetchFailed();
    }

    @Override
    public void onNoContactsInFile() {
        new ContactFetcherAsync((BaseActivity) selectContactView,
                this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override
    public void onSessionExpired() {
        selectContactView.hideLoading();
        selectContactView.onSessionExpired();
    }

    @Override
    public void onApiError(String message) {
        onError(message);
    }

    @Override
    public void checkIfAnyUserEligible(List<ContactItem> contactItemList, String referalId,String referralUserId) {
        selectContactView.showLoadingDialog();
        selectContactInteractor.checkIfAnyUserEligible(contactItemList, this, referalId,referralUserId);

    }

    @Override
    public void userExistCheckResult(int newUserCount, int oldUserCount, int invalidUserCount,List<ContactItem> contactItemList) {
        selectContactView.hideLoadingDialog();
        selectContactView.userExistCheckResult(newUserCount, oldUserCount, invalidUserCount,contactItemList);
    }

    @Override
    public void onError(String message) {
        selectContactView.hideLoadingDialog();
        selectContactView.onError(message);
    }

    @Override
    public void referUsers(List<ContactItem> contactItemList, String referalId, String comment,String referralUserId) {
        selectContactView.showLoadingDialog();
        selectContactInteractor.referUsers(contactItemList, referalId, comment, this,referralUserId);
    }

    @Override
    public void onReferSuccess(List<UserStatusItem> userStatusArray) {
        selectContactView.hideLoadingDialog();
        selectContactView.onReferSuccess(userStatusArray);

    }
}
