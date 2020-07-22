package com.news.newsapi.fragment.Sources

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
import com.news.newsapi.data.sources.Sources

import com.news.newsapi.util.selectAnimation
import kotlinx.android.synthetic.main.source_item.view.*
import java.lang.Exception
import java.util.*

class SourcesAdapter(
    private val sourcesList: MutableList<Sources>,
    private val callback: Callback
) : RecyclerView.Adapter<SourcesAdapter.SourcesViewHolder>() {

    private lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SourcesViewHolder {
        context = parent.context
        return SourcesViewHolder(
            LayoutInflater.from(context).inflate(R.layout.source_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: SourcesViewHolder, position: Int) {
        val source = sourcesList[position]
        holder.txtTitle.text=source.name
        holder.txtDescription.text=source.sourceDescription
        holder.txtUrl.text=source.sourceUrl

        if(source.language!=null)
        {
            try {
                val id=context.resources.getIdentifier(source.language!!.toUpperCase(Locale.getDefault()), "string", context.packageName)
                val countryFullName=context.getString( id);
                holder.txtLanguage.text=countryFullName
            }
            catch(ex:Exception){
                holder.txtLanguage.text=source.language
            }
        }
        else
            holder.txtLanguage.text=""


        Glide.with(context) //1

            .load("https://www.countryflags.io/${source.country}/flat/16.png")
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC) //3

            .into(holder.imgUnit)
        holder.cardView.tag=source.sourceUrl

        holder.cardView.setOnClickListener{
            selectAnimation(it)
            callback.onSelect(sourcesList[position])
        }

    }

    override fun getItemCount(): Int {
        return sourcesList.size
    }

    class SourcesViewHolder (view: View) : RecyclerView.ViewHolder(view) {

        val imgUnit: ImageView = view.imgFlag
        val txtTitle: TextView = view.txtUnitTitle
        val txtLanguage: TextView = view.txtLanguage
        val txtUrl: TextView = view.txtUrl
        val txtDescription: TextView = view.txtUnitDescription

        val cardView: CardView = view.unitItem
    }
    interface Callback {
        fun onSelect(source: Sources)
    }
}
