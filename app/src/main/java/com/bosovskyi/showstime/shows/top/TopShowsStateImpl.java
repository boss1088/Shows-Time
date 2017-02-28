package com.bosovskyi.showstime.shows.top;

import android.os.Parcel;
import android.os.Parcelable;

import com.bosovskyi.showstime.data.source.entity.ShowShortEntity;
import com.bosovskyi.showstime.library.presentation.mvp.state.StateObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by boss1088 on 2/28/17.
 */
public class TopShowsStateImpl implements StateObject {

    List<ShowShortEntity> topShows = new ArrayList<>();
    int page;
    int totalPages;

    public TopShowsStateImpl() {}

    private TopShowsStateImpl(Parcel in) {
        if (in.readByte() == 0x01) {
            topShows = new ArrayList<>();
            in.readList(topShows, ShowShortEntity.class.getClassLoader());
        } else {
            topShows = null;
        }
        page = in.readInt();
        totalPages = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (topShows == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(topShows);
        }
        dest.writeInt(page);
        dest.writeInt(totalPages);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<TopShowsStateImpl> CREATOR = new Parcelable.Creator<TopShowsStateImpl>() {
        @Override
        public TopShowsStateImpl createFromParcel(Parcel in) {
            return new TopShowsStateImpl(in);
        }

        @Override
        public TopShowsStateImpl[] newArray(int size) {
            return new TopShowsStateImpl[size];
        }
    };
}
