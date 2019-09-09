package br.com.spacexlaunches.util

import android.content.Context
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import javax.inject.Inject

class GlideImageLoader @Inject constructor(
    private val context: Context
) : ImageLoader {

    override fun loadImageFromUrl(
        imageUrl: String,
        imageView: ImageView,
        defaultDrawable: Int?
    ) {
        Glide.with(context)
            .load(imageUrl)
            .thumbnail(.1f)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(imageView)
    }

    override fun loadImageFromResource(
        @DrawableRes drawableRes: Int,
        imageView: ImageView
    ) {
        Glide.with(context)
            .load(drawableRes)
            .into(imageView)
    }

}
