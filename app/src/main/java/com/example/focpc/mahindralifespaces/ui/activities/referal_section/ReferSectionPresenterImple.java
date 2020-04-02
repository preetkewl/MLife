package com.example.focpc.mahindralifespaces.ui.activities.referal_section;

/**
 * Created by foc pc on 14-12-2017.
 */

public class ReferSectionPresenterImple implements ReferSectionPresenter,ReferSectionListener {
    private ReferSectionView referSectionView;
    private ReferSectionInteractor referSectionInteractor;

    public ReferSectionPresenterImple(ReferSectionView referSectionView) {
        this.referSectionView = referSectionView;
        this.referSectionInteractor = new ReferSectionInteractorImple();
    }

    @Override
    public void addnewUser(String mobile, String name, String email) {
        referSectionInteractor.addNewUser(mobile,name,email,this);
        referSectionView.showLoading();
    }

    @Override
    public void onNewUserAdded(UserItem userItem) {
        referSectionView.hideLoading();
        referSectionView.onUserAdded(userItem);
    }

    @Override
    public void onUserAdditionFailed(String message) {
        referSectionView.hideLoading();
        referSectionView.onUserAdditionFailed(message);
    }

    @Override
    public void onSessionExpired() {
        referSectionView.hideLoading();
        referSectionView.onSessionExpired();
    }

    @Override
    public void onApiError(String message) {
        onUserAdditionFailed(message);
    }
}
