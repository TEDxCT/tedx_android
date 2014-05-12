package com.tedx.capetown.app.core.service.request;

import android.content.Intent;
import android.os.Parcelable;

/**
 * Created by andrewpettey on 2014/05/02.
 */
public abstract class AbstractServiceRequest <T extends Parcelable> {

    private final T params;
    private final String action;
    private static final String PARAMS_KEY = "Params";

    public AbstractServiceRequest(Intent intent) {

        action = intent.getAction();
        params = intent.getParcelableExtra(PARAMS_KEY);

    }

    public AbstractServiceRequest(T params) {

        this.params = params;
        this.action = this.getClass().getName();
    }

    public AbstractServiceRequest() {

        this((T) null);
    }

    public T getParams() {

        return params;
    }

    public String getAction() {

        return action;
    }

    public final Intent createIntent() {

        Intent intent = new Intent(getAction());
        if (params != null) {
            intent.putExtra(PARAMS_KEY, params);
        }
        return intent;
    }
}