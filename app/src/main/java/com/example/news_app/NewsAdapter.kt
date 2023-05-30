package com.example.news_app

import android.app.Activity
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.app.ShareCompat
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide

class NewsAdapter(val a: Activity, val articles: ArrayList<Article>) :
    RecyclerView.Adapter<NewsAdapter.NewsVH>() {
    class  NewsVH(v: View):ViewHolder(v) {
        val parent: CardView = v.findViewById(R.id.parent_cr)
        val image: ImageView = v.findViewById(R.id.news_image)
        val title: TextView = v.findViewById(R.id.title_tv)
        val share : CardView = v.findViewById(R.id.share_cv)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsVH {
        val v = a.layoutInflater.inflate(R.layout.news_list_item,parent,false)
        return NewsVH(v)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    override fun onBindViewHolder(holder: NewsVH, position: Int) {
        holder.title.text=articles[position].title
        Glide
            .with(a)
            .load(articles[position].urlToImage)
            .error(R.drawable.broken_image)
            .into(holder.image)
        val website = articles[position].url
        holder.parent.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW,website.toUri())
            a.startActivity(i)
        }
        holder.share.setOnClickListener {
            ShareCompat
                .IntentBuilder(a)
                .setType("text/plain")
                .setChooserTitle("Share News With: ")
                .setText(articles[position].url)
                .startChooser()
        }
    }

}