package net.furkanakdemir.branchsample.image

import android.widget.ImageView

interface ImageLoader {

    fun load(imageView: ImageView, imageUrl: String)
}
