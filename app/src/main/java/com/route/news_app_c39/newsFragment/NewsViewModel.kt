package com.route.news_app_c39.newsFragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.route.news_app_c39.ViewMessage
import com.route.news_app_c39.api.ApiManager
import com.route.news_app_c39.api.modal.newsResponse.Article
import com.route.news_app_c39.api.modal.newsResponse.NewsResponse
import com.route.news_app_c39.api.modal.sourcesResponse.Source
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel :ViewModel() {
    val isLoadingVisible = MutableLiveData<Boolean>()
    val message = MutableLiveData<ViewMessage>()
    val newsLiveData = MutableLiveData<List<Article?>?>()
     fun loadNews(source:Source?) { // call api
       isLoadingVisible.value =true
        source?.id?.let {
                sourceId->
            ApiManager
                .getServices()
                .getNews(sources = sourceId)
                .enqueue(object : Callback<NewsResponse> {
                    override fun onResponse(
                        call: Call<NewsResponse>,
                        response: Response<NewsResponse>
                    ) {
                        isLoadingVisible
                        if (response.isSuccessful){
                            newsLiveData.value =(response.body()?.articles)
                            return
                        }
                        val responseJson =response.errorBody().toString()
                        val errorResponse = Gson().fromJson(responseJson, NewsResponse::class.java)
                      message.value = ViewMessage(
                          errorResponse.message?:"Something went wrong")
                    }

                    override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                        isLoadingVisible.value =false
                        message.value = ViewMessage(
                            t.message?:"Something went wrong")
                    }

                })
        }

    }
}