package com.xxmukulxx.to_dolist.common.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter

// ImageView adapters
@BindingAdapter("setImgFromURL")
fun ImageView.loadImage(imageUrl: String?) {
    val radius = getDimen(com.intuit.sdp.R.dimen._12sdp)
    setImgWithRadius(imageUrl, radius.toInt())
}

@BindingAdapter("setImageTint")
fun ImageView.setImageTint(tint: Int) {
    setColorFilter(tint)
}

@BindingAdapter("setRoundImgFromURL")
fun ImageView.setRoundImgFromURL(imageUrl: String?) {
    setImg(imageUrl)
}