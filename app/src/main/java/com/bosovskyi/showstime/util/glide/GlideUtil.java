package com.bosovskyi.showstime.util.glide;

import android.graphics.Bitmap;
import android.support.v4.content.ContextCompat;
import android.support.v7.graphics.Palette;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

/**
 * Created by boss1088 on 3/6/17.
 */

public class GlideUtil {

    private static final String BASE_URL = "https://image.tmdb.org/t/p/w300";

    public static void loadShowImageToView(ImageView view, String urlPath) {
        if (TextUtils.isEmpty(urlPath)) {
            return;
        }

        String url = BASE_URL + urlPath;
        Glide.with(view.getContext())
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .centerCrop()
                .into(view);
    }

    public static void loadShowImageToViewWithBackgroundPalette(ImageView view, ProgressBar progressBar, View background, String urlPath) {
        if (TextUtils.isEmpty(urlPath)) {
            return;
        }

        final int defaultColor = ContextCompat.getColor(view.getContext(), android.R.color.black);
        String url = BASE_URL + urlPath;
        progressBar.setVisibility(View.VISIBLE);
        Glide.with(view.getContext())
                .load(url)
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .centerCrop()
                .listener(new RequestListener<String, Bitmap>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<Bitmap> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Bitmap resource, String model, Target<Bitmap> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        progressBar.setVisibility(View.GONE);
                        updateBackgroundColorWithPaletteDarkMuted(resource, background, defaultColor);
                        return false;
                    }
                })
                .into(view);
    }

    public static void updateBackgroundColorWithPaletteDarkMuted(Bitmap resource, View view, int defaultColor) {
        Palette.from(resource).generate(palette -> {
            int color = palette.getDarkMutedColor(defaultColor);
            view.setBackgroundColor(color);
        });
    }
}
