package com.bosovskyi.showstime.shows.top;

import android.os.Parcel;
import android.os.Parcelable;

import com.bosovskyi.showstime.data.source.entity.ShowShortEntity;
import com.bosovskyi.showstime.library.presentation.mvp.state.StateObject;
import com.bosovskyi.showstime.library.presentation.mvp.state.impl.StateObjectImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by boss1088 on 2/28/17.
 */
public class TopShowsState extends StateObjectImpl {

    List<ShowShortEntity> topShows = new ArrayList<>();
    int page;
    int totalPages;
    public boolean loadingMore;

    public TopShowsState() {
        super();
    }

    private TopShowsState(Parcel in) {
        super(in);
        if (in.readByte() == 0x01) {
            topShows = new ArrayList<>();
            in.readList(topShows, ShowShortEntity.class.getClassLoader());
        } else {
            topShows = null;
        }
        page = in.readInt();
        totalPages = in.readInt();
        loadingMore = in.readByte() != 0x00;
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
        dest.writeByte((byte) (loadingMore ? 0x01 : 0x00));
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<TopShowsState> CREATOR = new Parcelable.Creator<TopShowsState>() {
        @Override
        public TopShowsState createFromParcel(Parcel in) {
            return new TopShowsState(in);
        }

        @Override
        public TopShowsState[] newArray(int size) {
            return new TopShowsState[size];
        }
    };
}
