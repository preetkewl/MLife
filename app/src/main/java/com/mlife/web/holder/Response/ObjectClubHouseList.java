package com.mlife.web.holder.Response;

import com.mlife.web.model.ClubHouseListResponse;

import java.util.Observable;

/**
 * Created by milagro on 10/7/2017.
 */

public class ObjectClubHouseList extends Observable {

    ClubHouseListResponse clubHouseListResponse = new ClubHouseListResponse();

    public ClubHouseListResponse getClubHouseListResponse() {
        return clubHouseListResponse;
    }

    public void setClubHouseListResponse(ClubHouseListResponse clubHouseListResponse) {
        this.clubHouseListResponse = clubHouseListResponse;
        setChanged();
        notifyObservers();
    }
}
