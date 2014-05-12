package com.tedx.capetown.app.core.service.params;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by andrewpettey on 2014/05/12.
 */
public class EmptyParams implements Parcelable {

    @Override
    public int describeContents() {

        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        // Do nothing

    }

    public static final Parcelable.Creator<EmptyParams> CREATOR = new Creator<EmptyParams>() {

        @Override
        public EmptyParams[] newArray(int size) {

            return new EmptyParams[size];
        }

        @Override
        public EmptyParams createFromParcel(Parcel source) {

            return new EmptyParams();
        }
    };
}