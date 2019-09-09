package br.com.spacexlaunches.util

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
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
            .into(imageView)
    }

}
