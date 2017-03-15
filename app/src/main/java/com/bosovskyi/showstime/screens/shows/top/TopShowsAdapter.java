package com.bosovskyi.showstime.screens.shows.top;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bosovskyi.showstime.R;
import com.bosovskyi.showstime.data.source.entity.ShowShortEntity;
import com.bosovskyi.showstime.databinding.ItemLoadingBinding;
import com.bosovskyi.showstime.databinding.ItemShowBinding;
import com.bosovskyi.showstime.library.presentation.ui.viewholder.BaseViewHolder;
import com.bosovskyi.showstime.library.presentation.ui.viewholder.LoadingViewHolder;

import java.util.List;

/**
 * Created by boss1088 on 3/6/17.
 */

public class TopShowsAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    public static final int VIEW_SHOW = 0;
    public static final int VIEW_LOADING = 1;

    private List<ShowShortEntity> items;

    private final TopShowsContract.View topShowsView;

    public TopShowsAdapter(List<ShowShortEntity> items, TopShowsContract.View topShowsView) {
        setHasStableIds(true);
        this.items = items;
        this.topShowsView = topShowsView;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_SHOW) {
            ItemShowBinding itemShowBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                    R.layout.item_show, parent, false);
            return new TopShowsViewHolder(itemShowBinding, topShowsView);
        } else {
            ItemLoadingBinding itemLoadingBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                    R.layout.item_loading, parent, false);
            return new LoadingViewHolder(itemLoadingBinding);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        if (getItemViewType(position) == VIEW_SHOW) {
            holder.bind(items.get(position), position);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).id == -1 ? VIEW_LOADING : VIEW_SHOW;
    }

    @Override
    public long getItemId(int position) {
        return items.get(position).id;
    }

    public void notifyItemsAdded(List<ShowShortEntity> items) {
        int startPosition = this.items.size() - items.size();

        notifyItemRangeInserted(startPosition, items.size());
    }

    public void addLoadingMore() {
        ShowShortEntity loading = new ShowShortEntity();
        loading.id = -1;

        int insertPosition = items.size();
        items.add(insertPosition, loading);

        notifyItemInserted(insertPosition);
    }

    public void removeLoadingMore() {
        if (items == null || items.size() == 0) {
            return;
        }

        if (items.get(items.size() - 1).id == -1) {
            items.remove(items.size() - 1);
            notifyItemRemoved(items.size());
        }
    }

    public void notifyItemsReplaced(List<ShowShortEntity> items) {
        notifyItemRangeInserted(0, items.size());
    }
}
