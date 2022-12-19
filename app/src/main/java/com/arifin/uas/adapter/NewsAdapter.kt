package com.arifin.uas.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.arifin.uas.R
import com.arifin.uas.activities.DetailNewsActivity
import com.arifin.uas.model.ModelArticle
import com.arifin.uas.util.Utils.DateFormat
import com.arifin.uas.util.Utils.DateTimeHourAgo
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.makeramen.roundedimageview.RoundedImageView
import kotlinx.android.synthetic.main.list_item_news.view.*

class NewsAdapter(private val modelArticles: MutableList<ModelArticle>, private val context: Context) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item_news, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = modelArticles[position]

        if (model.urlToImage == null) {
            holder.imageThumbnail.setImageResource(R.drawable.ic_broken_image)
        } else {
            Glide.with(context).load(model.urlToImage).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.imageThumbnail)
        }

        if (model.author == null) {
            holder.tvNameSource.text = model.modelSource?.name
        } else {
            holder.tvNameSource.text = model.author + "" + model.modelSource?.name
        }

        holder.tvTimeAgo.text = DateTimeHourAgo(model.publishedAt)
        holder.tvTitleNews.text = model.title
        holder.tvDateTime.text = DateFormat(model.publishedAt)
        holder.frameListNews.setOnClickListener {
            val intent = Intent(context, DetailNewsActivity::class.java)
            intent.putExtra(DetailNewsActivity.DETAIL_NEWS, modelArticles[position])
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return modelArticles.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var frameListNews: TextView
        var tvTimeAgo: TextView
        var tvNameSource: TextView
        var tvTitleNews: TextView
        var tvDateTime: TextView
        var imageThumbnail: RoundedImageView

        init {
            frameListNews = itemView.frameListNews
            tvTimeAgo = itemView.tvTimeAgo
            tvNameSource = itemView.tvNameSource
            tvTitleNews = itemView.tvTitleNews
            tvDateTime = itemView.tvDateTime
            imageThumbnail = itemView.imageThumbnail
        }
    }

}
