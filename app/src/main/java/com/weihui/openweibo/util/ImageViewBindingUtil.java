package com.weihui.openweibo.util;

import android.databinding.BindingAdapter;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;


public class ImageViewBindingUtil {

    private static String TAG = "ImageViewBindingUtil";

    @BindingAdapter("roundImage")
    public static void loadRoundImage(ImageView view, String url) {

        Log.i(TAG, "loadRoundImage:" + url);
        Glide.with(view.getContext())
                .load(url)
                .apply(RequestOptions.circleCropTransform())
                .into(view);
    }

    @BindingAdapter("loadImage")
    public static void loadImage(ImageView view, String url) {
        Glide.with(view.getContext())
                .load(url)
                .into(view);
    }


}
