package com.tedx.capetown.app.core.service.params;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by andrewpettey on 2014/05/02.
 */
public class AbstractParams implements Parcelable {
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }
}
