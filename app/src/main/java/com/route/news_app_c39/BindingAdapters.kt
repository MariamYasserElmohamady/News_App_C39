package com.route.news_app_c39

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import retrofit2.http.Url
@BindingAdapter("app:imageUrl")
fun loadImageFromUrl(imageView: ImageView,url: String?){
    Glide.with(imageView)
        .load(url)
        .into(imageView)

}