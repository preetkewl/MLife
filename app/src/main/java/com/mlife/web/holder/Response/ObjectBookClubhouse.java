package com.mlife.web.holder.Response;

import com.mlife.web.model.BookClubhouseResponse;

import java.util.Observable;

/**
 * Created by milagro on 10/7/2017.
 */

public class ObjectBookClubhouse extends Observable {

    BookClubhouseResponse bookClubhouseResponse = new BookClubhouseResponse();

    public BookClubhouseResponse getBookClubhouseResponse() {
        return bookClubhouseResponse;
    }

    public void setBookClubhouseResponse(BookClubhouseResponse bookClubhouseResponse) {
        this.bookClubhouseResponse = bookClubhouseResponse;
        setChanged();
        notifyObservers();
    }
}
