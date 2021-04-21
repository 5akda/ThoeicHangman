package tech.parzival48.thoeic.utils

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadFromDrawable(resId: Int) {
    Glide.with(this)
        .load("")
        .placeholder(resId)
        .into(this)
}

fun View.visible(value: Boolean) {
    visibility = if (value) View.VISIBLE else View.GONE
}