package br.com.spacexlaunches.util

import android.widget.ImageView

interface ImageLoader {

    fun loadImageFromUrl(
        imageUrl: String,
        imageView: ImageView,
        defaultDrawable: Int? = null
    )

}
