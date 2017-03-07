package com.bosovskyi.showstime.shows.top;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bosovskyi.showstime.R;
import com.bosovskyi.showstime.data.source.entity.ShowShortEntity;
import com.bosovskyi.showstime.databinding.ItemShowBinding;

import java.util.List;

/**
 * Created by boss1088 on 3/6/17.
 */

public class TopShowsAdapter extends RecyclerView.Adapter<TopShowsViewHolder> {

    private List<ShowShortEntity> items;

    private final TopShowsContract.View topShowsView;

    public TopShowsAdapter(List<ShowShortEntity> items, TopShowsContract.View topShowsView) {
        setHasStableIds(true);
        this.items = items;
        this.topShowsView = topShowsView;
    }

    @Override
    public TopShowsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemShowBinding itemShowBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_show, parent, false);
        return new TopShowsViewHolder(itemShowBinding, topShowsView);
    }

    @Override
    public void onBindViewHolder(TopShowsViewHolder holder, int position) {
        holder.bind(items.get(position), position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public long getItemId(int position) {
        return items.get(position).id;
    }

    public void setItems(List<ShowShortEntity> items) {
        this.items = items;
    }

    public void replaceItems(List<ShowShortEntity> items) {
        setItems(items);
        notifyDataSetChanged();
    }
}
