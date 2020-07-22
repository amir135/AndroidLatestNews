package com.news.newsapi.fragment.news

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.news.newsapi.R
import com.news.newsapi.data.Dto.NewsSource
import com.news.newsapi.util.selectAnimation
import kotlinx.android.synthetic.main.news_item.view.*
import java.text.SimpleDateFormat

class NewsAdapter (
    private val newsList: MutableList<NewsSource>,
    private val callback: NewsAdapter.Callback
) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        context = parent.context
        return NewsViewHolder(
            LayoutInflater.from(context).inflate(R.layout.news_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = newsList[position]
        holder.txtTitle.text=news.title
        holder.txtDescription.text=news.description
//        if(news.sources!!.name==null)
//            holder.txtAuthor.text="-"
//        else
        holder.txtAuthor.text=news.name


        val simpleDate = SimpleDateFormat("dd MMM yyyy HH:mm")

        val strDt: String = simpleDate.format(news.publishedAt)
        holder.txtPublishedDate.text=strDt

        Glide.with(context) //1

            .load(news.urlToImage)
            .placeholder(R.drawable.preload)
            .error(R.drawable.notfound)

            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC) //3

            .into(holder.imgUnit)
        holder.cardView.tag=news.url
        holder.cardView.setOnClickListener{
            selectAnimation(it)
            callback.onSelect(newsList[position].url)
        }

    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    class NewsViewHolder (view: View) : RecyclerView.ViewHolder(view) {

        val imgUnit: ImageView = view.imgLesson
        val txtTitle: TextView = view.txtLessonTitle
        val txtDescription: TextView = view.txtLessonDescription
        val txtPublishedDate: TextView = view.txtPublishedDate
        val txtAuthor: TextView = view.txtAuthor

        val cardView: CardView = view.lessonItem
    }
    interface Callback {
        fun onSelect(url: String)
    }
}
