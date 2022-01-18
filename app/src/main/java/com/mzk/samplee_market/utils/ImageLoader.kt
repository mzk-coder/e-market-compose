package com.mzk.samplee_market.utils

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.mzk.samplee_market.R


@Composable
fun imageLoader(url: String) : MutableState<Bitmap?> {

    val context = LocalContext.current

    val bitmapState: MutableState<Bitmap?> = remember { mutableStateOf(null) }


    Glide.with(context)
        .asBitmap()
        .placeholder(R.drawable.placeholder)
        .load(url)
        .into(object : CustomTarget<Bitmap>() {
            override fun onLoadCleared(placeholder: Drawable?) { }
            override fun onResourceReady(
                resource: Bitmap,
                transition: Transition<in Bitmap>?
            ) {
                bitmapState.value = resource
            }
        })

    return bitmapState

}