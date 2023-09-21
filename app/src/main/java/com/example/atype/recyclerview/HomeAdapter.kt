package com.example.atype.recyclerview

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.atype.data.api.Content.DateFormat
import com.example.atype.data.api.KakaoImage
import com.example.atype.databinding.NewsBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.executor.GlideExecutor.UncaughtThrowableStrategy.LOG
import com.example.atype.UI.MainActivity
import com.example.atype.data.api.SearchModel

class HomeAdapter (private val mitem: Context) : RecyclerView.Adapter<HomeAdapter.HomeHolder>() {


    var items = ArrayList<SearchModel>()
    fun clearItem() {
        items.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeHolder {
        val binding =
            NewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeHolder(binding)
    }// ViewHolder의 값들을 모두 가져와서 view에 연결시켜준다. 2번

    override fun onBindViewHolder(holder: HomeHolder, position: Int) {
        val currentItem = items[position]
        Glide.with(mitem)
            .load(currentItem.url)
            .into(holder.img_news)
        holder.news_title.text = currentItem.title
        holder.news_timeline.text = DateFormat(
            currentItem.dateTime,"YYYY-MM-DD'T'hh:mm:ss.SSS+09:00","yyyy-mm-dd HH:mm:ss"
        )
    }

    override fun getItemCount() = items.size


    inner class HomeHolder(private val binding: NewsBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        var img_news: ImageView = binding.newsImg
        var news_title: TextView = binding.newsTxt
        var news_timeline: TextView = binding.timelineTxt
        var heart: ImageView = binding.imgHeart
        var cons: ConstraintLayout = binding.cons1

        init {
            cons.setOnClickListener(this)
            img_news.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            val position = adapterPosition.takeIf { it != RecyclerView.NO_POSITION }?: return
            val item = items[position]
            item.islike = !item.islike
            if (item.islike){
                (mitem as MainActivity).addLikedItem(item)
            }else{
                (mitem as MainActivity).removeLikedItem(item)
            }
            notifyItemChanged(position)

        }
    }
}
