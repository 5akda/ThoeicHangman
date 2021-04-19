package tech.parzival48.thoeic.utils

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadFromDrawable(resId: Int) {
    Glide.with(this)
        .load("")
        .placeholder(resId)
        .into(this)
}