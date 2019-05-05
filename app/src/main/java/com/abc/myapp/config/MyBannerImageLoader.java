package com.abc.myapp.config;

import android.content.Context;
import android.widget.ImageView;

import com.abc.myapp.R;
import com.bumptech.glide.Glide;
import com.youth.banner.loader.ImageLoader;

/**
 * @author imjackzhao@gmail.com
 * @date 2019/5/5
 */
public class MyBannerImageLoader extends ImageLoader {

    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        // 使用Glide加载图片
        Glide.with(context).load(path).error(R.drawable.default_image).into(imageView);
    }
}
