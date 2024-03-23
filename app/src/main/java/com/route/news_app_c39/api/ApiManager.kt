package com.route.news_app_c39.api

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiManager {

    // build retrofit
    private var retrofit: Retrofit

    init {
        // interceptor
        // interrupt request and take parameters to print ,
        // interrupt response and take data to print
        val logging = HttpLoggingInterceptor(object :
            HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Log.e("api->", message)
            }

        })
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl("https://newsapi.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // we use it to get apis
    fun getServices(): WebServices {
        return retrofit.create(WebServices::class.java)
    }
}