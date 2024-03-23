package com.route.news_app_c39.newsFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.route.news_app_c39.ViewMessage
import com.route.news_app_c39.api.modal.newsResponse.Article
import com.route.news_app_c39.api.modal.sourcesResponse.Source
import com.route.news_app_c39.databinding.FragmentNewsBinding

class NewsFragment : Fragment() {

    lateinit var viewBinding: FragmentNewsBinding
    lateinit var viewModal: NewsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModal = ViewModelProvider(this).get(NewsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentNewsBinding.inflate(
            inflater,
            container,
            false
        )
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observeLiveData()

    }

    private fun observeLiveData() {
        viewModal.message.observe(viewLifecycleOwner) {
            showMessage(it)
        }
        viewModal.isLoadingVisible.observe(viewLifecycleOwner) {
            changeLoadingVisibility(it)
        }
        viewModal.newsLiveData.observe(
            viewLifecycleOwner, {
                showNewsList(it)
            }
        )
    }

    private fun initViews() {
        viewBinding.newsRv.adapter = adapter
    }

    var source: Source? = null
    fun changeSource(source: Source) { // click on tab
        this.source = source
        // call Api
        //loadNews()
        viewModal.loadNews(source)
    }

    val adapter = NewsAdapter(null)

    private fun showNewsList(articles: List<Article?>?) {
        adapter.changeData(articles)
    }

    private fun showMessage(message: ViewMessage) {
        viewBinding.errorView.isVisible = true
        viewBinding.errorMessage.text = message.message
        viewBinding.tryAgain.text = message.posActionName
    }

    private fun changeLoadingVisibility(isLoadingVisible: Boolean) {
        viewBinding.progressBar.isVisible = isLoadingVisible
    }

//    private fun loadNews() { // call api
//        changeLoadingVisibility(true)
//        source?.id?.let {
//            sourceId->
//            ApiManager
//                .getServices()
//                .getNews(sources = sourceId)
//                .enqueue(object :Callback<NewsResponse>{
//                    override fun onResponse(
//                        call: Call<NewsResponse>,
//                        response: Response<NewsResponse>
//                    ) {
//                        changeLoadingVisibility(false)
//                        if (response.isSuccessful){
//                            showNewsList(response.body()?.articles)
//                            return
//                        }
//                        val responseJson =response.errorBody().toString()
//                        val errorResponse =Gson().fromJson(responseJson,NewsResponse::class.java)
//                        showError(errorResponse.message)
//                    }
//
//                    override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
//                        changeLoadingVisibility(false)
//                        showError(t.message)
//                    }
//
//                })
//        }
//
//    }

}