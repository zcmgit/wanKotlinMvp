package com.kotlin.test.weigets

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestOptions
import com.youth.banner.loader.ImageLoader

/**
 * @author zcm
 * @create 2018/11/20
 * @Describe
 */

class GlideImageLoader : ImageLoader(){
    override fun displayImage(context: Context?, path: Any?, imageView: ImageView?) {
        if (imageView != null) {
            if (context != null) {
                Glide.with(context)
                        .load(path as String)
                        .into(imageView)
            }
        }
    }
}
