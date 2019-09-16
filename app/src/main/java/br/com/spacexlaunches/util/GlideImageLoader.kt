package br.com.spacexlaunches.util

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class GlideImageLoader : ImageLoader {

    override fun loadImageFromUrl(
        imageUrl: String,
        imageView: ImageView,
        defaultDrawable: Int?
    ) {
        Glide.with(imageView.context)
            .load(imageUrl)
            .thumbnail(.1f)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(imageView)
    }

    override fun loadImageFromResource(
        @DrawableRes drawableRes: Int,
        imageView: ImageView
    ) {
        Glide.with(imageView.context)
            .load(drawableRes)
            .into(imageView)
    }

}
