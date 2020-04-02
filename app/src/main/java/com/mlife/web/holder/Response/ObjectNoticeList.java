package com.mlife.web.holder.Response;

import com.mlife.web.model.NoticeListResponse;

import java.util.Observable;

/**
 * Created by milagro on 10/5/2017.
 */

public class ObjectNoticeList extends Observable {
    NoticeListResponse noticeListResponse = new NoticeListResponse();

    public NoticeListResponse getNoticeListResponse() {return noticeListResponse;}

    public void setNoticeListResponse(NoticeListResponse noticeListResponse) {
        this.noticeListResponse = noticeListResponse;
        setChanged();
        notifyObservers();
    }
}