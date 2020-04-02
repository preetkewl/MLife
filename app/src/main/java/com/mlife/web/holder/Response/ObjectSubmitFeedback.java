package com.mlife.web.holder.Response;

import com.mlife.web.model.SubmitFeedbackResponse;

import java.util.Observable;

/**
 * Created by milagro on 11/2/2017.
 */

public class ObjectSubmitFeedback extends Observable {

    SubmitFeedbackResponse submitFeedbackResponse = new SubmitFeedbackResponse();

    public SubmitFeedbackResponse getSubmitFeedbackResponse() {
        return submitFeedbackResponse;
    }

    public void setSubmitFeedbackResponse(SubmitFeedbackResponse submitFeedbackResponse) {
        this.submitFeedbackResponse = submitFeedbackResponse;
        setChanged();
        notifyObservers();
    }
}
