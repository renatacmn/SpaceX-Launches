package br.com.spacexlaunches.util

import android.widget.ImageView
import androidx.annotation.DrawableRes

interface ImageLoader {

    fun loadImageFromUrl(
        imageUrl: String,
        imageView: ImageView,
        defaultDrawable: Int? = null
    )

    fun loadImageFromResource(
        @DrawableRes drawableRes: Int,
        imageView: ImageView
    )

}
