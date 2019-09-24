package net.furkanakdemir.branchsample.image

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import javax.inject.Inject

class GlideImageLoader @Inject
constructor(private val context: Context) : ImageLoader {

    override fun load(imageView: ImageView, imageUrl: String) {
        Glide.with(context)
            .load(imageUrl)
            .into(imageView)
    }
}
