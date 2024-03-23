package com.route.news_app_c39.newsSources

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.route.news_app_c39.ViewMessage
import com.route.news_app_c39.api.ApiManager
import com.route.news_app_c39.api.modal.sourcesResponse.Source
import com.route.news_app_c39.api.modal.sourcesResponse.SourcesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
//ViewModel is the brain of View
class NewsSourcesViewModel : ViewModel() {
    // LiveData is imMutable
    val isLoadingVisible = MutableLiveData<Boolean>()
    val message = MutableLiveData<ViewMessage>()
    val newsSourcesLiveData = MutableLiveData<List<Source?>?>()
    fun getNewsSources() {

        // execute return response
        //(execute work in the same thread you are in)
        // (if you are in main thread and used execute app will crash (delay)) crash if Apis delayed
        // should use execute() if you are in background thread
        // enqueue work in background
        // we are in main thread we cant make heavy work in main thread
        // enqueue take parameter callback ->take api and run in background thread and
        // when response return ,enqueue return response in callback

        //enter in onFailure when cant reach server
        //enter in onResponse 2 cases 1.error from server or 2. response

        isLoadingVisible.value = true
            ApiManager.getServices().getNewsSources()
                .enqueue(object : Callback<SourcesResponse> {
                    override fun onResponse(
                        call: Call<SourcesResponse>,
                        response: Response<SourcesResponse>
                    ) {
                        // response.body().message -> message from server in body
                        // response.message() -> message from http response 401 unauthorized
                        isLoadingVisible.value = false
                        if (response.isSuccessful) {
                            newsSourcesLiveData.value = (response.body()?.sources)
                            return
                        }
                        // error from server
                        // response code any thing else 200
                        val responseJson = response.errorBody()?.string() // response json as string
                        //fromJson (string,class want to covert to it)
                        val errorResponse = Gson().fromJson(
                            responseJson,
                            SourcesResponse::class.java
                        ) // convert responseJson to object from SourcesResponse
                        message.value = ViewMessage(
                            message = errorResponse.message ?: "SomeThingWent Wrong",
                        )
                    }

                    // cant reach to server
                    override fun onFailure(
                        call: Call<SourcesResponse>,
                        t: Throwable
                    ) {
                        isLoadingVisible.value = false
                        message.value = ViewMessage(
                            message = t.message ?: "something went wrong",
                            posActionName = "Try Again",
                            posAction = {
                                getNewsSources()
                            }
                        )
                    }

                }
                )

        }
    }
//        viewModelScope.launch(Dispatchers.IO) {
//           try {
//               val response =
//                   ApiManager.getServices().getNewsSources()
//               newsSourcesLiveData.postValue(response.sources)
//           }catch (e:HttpException){
//               val responseJson = e.response()?.errorBody()?.string()
//               val errorResponse =Gson().fromJson(
//                   responseJson,
//                   SourcesResponse::class.java
//               )
//               message.postValue(ViewMessage(
//                   message = errorResponse.message ?: "Some Thing Went wrong"
//               ))
//           }catch (e:Exception) {
//               message.postValue(ViewMessage(
//                   message = e.message ?: "Some Thing Went wrong"
//               ))
//           }
//           finally {
//               isLoadingVisible.value =false
//           }
//           }