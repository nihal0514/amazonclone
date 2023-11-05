package com.example.amazonclone.utils

import android.content.Context
import android.util.Log
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.amazonclone.R

fun getProgressDrawable(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 10f
        centerRadius = 50f
        start()
    }
}

fun ImageView.loadImage(uri: String?, progressDrawable: CircularProgressDrawable) {
    val oldValue = "localhost"
    val newValue = "192.168.1.41"

    val output = uri?.replace(oldValue, newValue)
    val options = RequestOptions()
        .placeholder(progressDrawable)
        .error(R.mipmap.ic_launcher_round)
    Glide.with(this.context)
        .setDefaultRequestOptions(options)
        .load(output)
        .into(this)
}

fun ImageView.loadImage1(uri: String?) {

    val oldValue = "localhost"
    val newValue = "192.168.1.41"

    val output = uri?.replace(oldValue, newValue)
    val options = RequestOptions()
        .error(R.mipmap.ic_launcher_round)
    Glide.with(this.context)
        .setDefaultRequestOptions(options)
        .load(output)
        .into(this)
}
fun loge(message: Any) {
    Log.e("Demo App Log", "" + message.toString())

}