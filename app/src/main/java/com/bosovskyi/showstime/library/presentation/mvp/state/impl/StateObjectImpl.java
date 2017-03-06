package com.bosovskyi.showstime.library.presentation.mvp.state.impl;

import android.os.Parcel;
import android.os.Parcelable;

import com.bosovskyi.showstime.library.presentation.mvp.state.StateObject;

/**
 * Created by boss1088 on 3/6/17.
 */

public class StateObjectImpl implements StateObject {

    public boolean loadedFirstTime;

    public StateObjectImpl() {

    }

    protected StateObjectImpl(Parcel in) {
        loadedFirstTime = in.readByte() != 0x00;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (loadedFirstTime ? 0x01 : 0x00));
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<StateObjectImpl> CREATOR = new Parcelable.Creator<StateObjectImpl>() {
        @Override
        public StateObjectImpl createFromParcel(Parcel in) {
            return new StateObjectImpl(in);
        }

        @Override
        public StateObjectImpl[] newArray(int size) {
            return new StateObjectImpl[size];
        }
    };
}
