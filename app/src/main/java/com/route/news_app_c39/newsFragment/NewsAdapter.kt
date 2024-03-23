package com.route.news_app_c39.newsFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.route.news_app_c39.R
import com.route.news_app_c39.api.modal.newsResponse.Article
import com.route.news_app_c39.api.modal.newsResponse.NewsResponse
import com.route.news_app_c39.databinding.ItemNewsBinding


class NewsAdapter (var newsList: List<Article?>?): RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    class NewsViewHolder(val itemBinding: ItemNewsBinding):ViewHolder(itemBinding.root){
        fun bind(news: Article?) {
            itemBinding.newsItem =news
//            itemBinding.title.text =news?.title
//            itemBinding.author.text =news?.author
//            itemBinding.date.text =news?.publishedAt
            itemBinding.executePendingBindings()
            // glide take ,context,activity,fragment , view
//            Glide.with(itemView)
//                .load(news?.urlToImage)
//                .placeholder(R.drawable.ic_launcher_foreground)
//                .into(itemBinding.image)



        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val itemBinding =ItemNewsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return NewsViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
       return newsList?.size ?:0
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
       holder.bind(newsList?.get(position))
    }

    fun changeData(articles: List<Article?>?) {
        newsList = articles
        // to refresh
        notifyDataSetChanged()

    }
}