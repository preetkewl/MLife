package com.mlife.web.holder.Response;

import com.mlife.web.model.HomeBannerListResponse;

import java.util.Observable;

/**
 * Created by milagro on 11/8/2017.
 */

public class ObjectHomeBannerList extends Observable {

    HomeBannerListResponse homeBannerListResponse = new HomeBannerListResponse();

    public HomeBannerListResponse getHomeBannerListResponse() {
        return homeBannerListResponse;
    }

    public void setHomeBannerListResponse(HomeBannerListResponse homeBannerListResponse) {
        this.homeBannerListResponse = homeBannerListResponse;
        setChanged();
        notifyObservers();
    }

}