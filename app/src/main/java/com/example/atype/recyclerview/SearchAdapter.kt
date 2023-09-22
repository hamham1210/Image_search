package com.example.atype.recyclerview

import android.content.Context
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.atype.R
import com.example.atype.UI.MainActivity
import com.example.atype.data.api.Content
import com.example.atype.data.api.SearchModel
import com.example.atype.databinding.NewsBinding

class SearchAdapter(var mContext: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var items = mutableListOf<SearchModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = NewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is SearchHolder) {
            Glide.with(mContext)
                .load(items[position].url)
                .into(holder.img_news)
            holder.title.text = items[position].title
            holder.timeline.text =
                Content.DateFormat(
                    items[position].dateTime,
                    "YYYY-MM-DD'T'hh:mm:ss.SSS+09:00",
                    "yyyy-mm-dd HH:mm:ss"
                )
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class SearchHolder(binding: NewsBinding) : RecyclerView.ViewHolder(binding.root) {
        var img_news: ImageView = binding.newsImg
        var heart: ImageView = binding.imgHeart
        var title: TextView = binding.newsTxt
        var timeline: TextView = binding.timelineTxt

        init {
            heart.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    items.removeAt(position)
                    notifyItemRemoved(position)
                    heart.setBackgroundResource(R.drawable.icon_heart_full)
                }
            }

        }
    }
}

