package com.route.news_app_c39.newsSources

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayout
import com.route.news_app_c39.R
import com.route.news_app_c39.ViewMessage

import com.route.news_app_c39.api.modal.sourcesResponse.Source
import com.route.news_app_c39.databinding.FragmentNewsSourcesBinding
import com.route.news_app_c39.newsFragment.NewsFragment

class NewsSourcesFragment : Fragment() {
    lateinit var viewBinding: FragmentNewsSourcesBinding
    lateinit var viewModel: NewsSourcesViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // []access operator
        viewModel = ViewModelProvider(this)[NewsSourcesViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentNewsSourcesBinding.inflate(
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
        viewModel.getNewsSources()
        // getNewsSources()
    }

    private fun observeLiveData() {
        // الاحظ changes
        viewModel.isLoadingVisible.observe(viewLifecycleOwner,
            object : Observer<Boolean> {
                override fun onChanged(value: Boolean) {
                    changeLoadingVisibility(value)
                }
            })
        viewModel.message.observe(viewLifecycleOwner, {
            showMessage(it)
        })
        viewModel.newsSourcesLiveData.observe(
            viewLifecycleOwner){
                showNewsSources(it)
            }
    }

    val newsFragment = NewsFragment()

    private fun initViews() {
        // we are already in fragment
        childFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, newsFragment)
            .commit()

//        viewBinding.tryAgain.setOnClickListener {
//            it-> viewBinding.errorView.isVisible =false
//
//            //getNewsSources()
//        }

    }

    fun changeLoadingVisibility(isLoadingVisible: Boolean) {
        viewBinding.progressBar.isVisible = isLoadingVisible
    }

    private fun showNewsSources(sources: List<Source?>?) {
        viewBinding.errorView.isVisible = false
        viewBinding.progressBar.isVisible = false
        sources?.forEach { source ->
            // because tab layout configurations
            val tab = viewBinding.tabLayout.newTab()
            tab.text = source?.name
            tab.tag = source
            viewBinding.tabLayout.addTab(tab)


        }
        viewBinding.tabLayout.addOnTabSelectedListener(
            object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    val source = tab?.tag as Source
                    newsFragment.changeSource(source)
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {

                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                    // refresh
                    val source = tab?.tag as Source
                    newsFragment.changeSource(source)
                }

            })
        viewBinding.tabLayout.getTabAt(0)?.select()
    }

    private fun showMessage(message: ViewMessage) {
        viewBinding.errorView.isVisible = true
        viewBinding.errorMessage.text = message.message
        viewBinding.tryAgain.text = message.posActionName
        viewBinding.tryAgain.setOnClickListener {
            message.posAction?.invoke()
        }
    }

//    private fun getNewsSources() {
//        changeLoadingVisibility(true)
//        // execute return response
//        //(execute work in the same thread you are in)
//        // (if you are in main thread and used execute app will crash (delay)) crash if Apis delayed
//        // should use execute() if you are in background thread
//        // enqueue work in background
//        // we are in main thread we cant make heavy work in main thread
//        // enqueue take parameter callback ->take api and run in background thread and
//        // when response return ,enqueue return response in callback
//
//        //enter in onFailure when cant reach server
//        //enter in onResponse 2 cases 1.error from server or 2. response
//        ApiManager
//            .getServices()
//            .getNewsSources()
//            .enqueue(object : Callback<SourcesResponse> {
//                override fun onResponse(
//                    call: Call<SourcesResponse>,
//                    response: Response<SourcesResponse>
//                ) {
//                    // response.body().message -> message from server in body
//                    // response.message() -> message from http response 401 unauthorized
//                    changeLoadingVisibility(false)
//                    if (response.isSuccessful) {
//                        showNewsSources(response.body()?.sources)
//                        return
//                    }
//                    // error from server
//                    // response code any thing else 200
//                    val responseJson = response.errorBody()?.string() // response json as string
//                    //fromJson (string,class want to covert to it)
//                    val errorResponse = Gson().fromJson(
//                        responseJson,
//                        SourcesResponse::class.java
//                    ) // convert responseJson to object from SourcesResponse
//                    showError(errorResponse.message)
//                }
//                // cant reach to server
//                override fun onFailure(
//                    call: Call<SourcesResponse>, t: Throwable
//                ) {
//                    changeLoadingVisibility(false)
//                    showError(t.message)
//                }
//
//            })
//
//    }

}