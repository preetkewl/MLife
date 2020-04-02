package com.example.focpc.mahindralifespaces.ui.activities.promote_us;

/**
 * Created by foc pc on 14-12-2017.
 */

public class PromoteUsPresenterImple implements PromoteUsPresenter,PromoteUsListener {

    private PromoteUsView promoteUsView;
    private PromoteUsInteractor promoteUsInteractor;

    public PromoteUsPresenterImple(PromoteUsView promoteUsView) {
        this.promoteUsView = promoteUsView;
        this.promoteUsInteractor = new PromoteUsInteractorImple();
    }

    @Override
    public void promoteContact(String ex_user_id, String designation, String comments, String contact_no,
                               String contact_name, String contact_email, String institute_type,
                               String institute_name,String city) {
        promoteUsView.showLoading();
        promoteUsInteractor.promoteContact(ex_user_id,designation,comments,contact_no,contact_name,contact_email,
                institute_type,institute_name,city,this);


    }

    @Override
    public void onSessionExpired() {
        promoteUsView.hideLoading();
        promoteUsView.onSessionExpired();

    }

    @Override
    public void onApiError(String message) {
        onFailedToPromote(message);

    }

    @Override
    public void onPromoted() {
        promoteUsView.hideLoading();
        promoteUsView.onContactPromoted();
    }

    @Override
    public void onFailedToPromote(String message) {
        promoteUsView.hideLoading();
        promoteUsView.onPromoteFailed(message);
    }
}
