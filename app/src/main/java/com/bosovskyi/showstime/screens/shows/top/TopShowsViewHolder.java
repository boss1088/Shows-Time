package com.bosovskyi.showstime.screens.shows.top;

import android.view.View;

import com.bosovskyi.showstime.data.source.entity.ShowShortEntity;
import com.bosovskyi.showstime.databinding.ItemShowBinding;
import com.bosovskyi.showstime.library.presentation.ui.viewholder.BaseViewHolder;
import com.bosovskyi.showstime.util.glide.GlideUtil;

/**
 * Created by boss1088 on 3/6/17.
 */

public class TopShowsViewHolder extends BaseViewHolder<ItemShowBinding, ShowShortEntity> {

    private TopShowsContract.View topShowsView;

    public TopShowsViewHolder(ItemShowBinding binding, TopShowsContract.View topShowsView) {
        super(binding);
        this.topShowsView = topShowsView;
    }

    @Override
    public void bind(ShowShortEntity showShortEntity, int position) {
        View root = binding.getRoot();

        GlideUtil.loadShowImageToViewWithBackgroundPalette(binding.showCover, binding.imageLoading, binding.infoBackground, showShortEntity.posterPath);
        binding.showName.setText(showShortEntity.name);
        binding.showRating.setText(String.valueOf(showShortEntity.voteAverage));

        root.setOnClickListener(v -> topShowsView.showSelected(showShortEntity, binding.showCover));
    }
}
