package com.bosovskyi.showstime.screens.details;

import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.graphics.Palette;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bosovskyi.showstime.R;
import com.bosovskyi.showstime.data.source.entity.ShowShortEntity;
import com.bosovskyi.showstime.databinding.FragmentShowDetailBinding;
import com.bosovskyi.showstime.library.presentation.ui.fragment.BaseBindingFragment;
import com.bosovskyi.showstime.util.glide.GlideUtil;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

/**
 * Created by boss1088 on 3/13/17.
 */

public class ShowDetailsFragment
        extends BaseBindingFragment<FragmentShowDetailBinding> {

    private ShowShortEntity showShortEntity;

    private Handler handler;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        handler = new Handler();

        showShortEntity = getArguments().getParcelable("show");
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ViewCompat.setTransitionName(binding.showCover, "image");
        loadShowImageToView(binding.showCover, showShortEntity.posterPath);

        binding.overview.setText(showShortEntity.overview);

        getActivity().setTitle(showShortEntity.name);
    }

    public void loadShowImageToView(ImageView view, String urlPath) {
        if (TextUtils.isEmpty(urlPath)) {
            return;
        }

        String url = GlideUtil.BASE_URL + urlPath;
        Glide.with(view.getContext())
                .load(url)
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .listener(new RequestListener<String, Bitmap>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<Bitmap> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Bitmap resource, String model, Target<Bitmap> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        Palette.from(resource).generate(palette -> applyPalette(palette));
                        return false;
                    }
                })
                .into(view);

        handler.postDelayed(() -> getActivity().supportStartPostponedEnterTransition(), 500);
    }

    private void applyPalette(Palette palette) {
        binding.screenBackground.setBackgroundColor(
                palette.getDarkMutedColor(
                        ContextCompat.getColor(
                                getContext(), android.R.color.black)));
        ((ShowDetailsActivity) getActivity()).setToolbarColor(
                palette.getDarkVibrantColor(
                    ContextCompat.getColor(
                        getContext(), android.R.color.black)));
        getActivity().supportStartPostponedEnterTransition();
    }

    @Override
    protected FragmentShowDetailBinding initBinding(LayoutInflater inflater, @Nullable ViewGroup container) {
        return DataBindingUtil.inflate(inflater, R.layout.fragment_show_detail, container, false);
    }
}
