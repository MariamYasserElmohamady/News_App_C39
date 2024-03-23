package com.route.news_app_c39

data class ViewMessage(
    val message: String,
    val posActionName:String?=null,
    //callback fun
    val posAction:(()->Unit)?=null,
    val negActionName:String?=null,
    val negAction:(()->Unit)?=null,
    val isDismissible:Boolean =true,
    )