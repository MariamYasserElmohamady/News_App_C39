package com.route.news_app_c39.api

import com.route.news_app_c39.api.modal.newsResponse.NewsResponse
import com.route.news_app_c39.api.modal.sourcesResponse.SourcesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
// create Apis in Web Service Class interface
interface WebServices {
    // request type , url name
    @GET("v2/top-headlines/sources")
    fun getNewsSources(
        // parameters
        // @Query (parameter types)
        // return (Call<T>)
        //Map json to classes
        @Query("apiKey")
        apiKey:String =Constants.apiKey,
        ):Call<SourcesResponse> //T template parameter

    // request type , url name
    @GET("v2/everything")
    fun getNews(
        @Query("apiKey")
        apiKey: String = Constants.apiKey, // default parameter
        @Query("sources")
        sources:String,
    ):Call<NewsResponse> //return type
}