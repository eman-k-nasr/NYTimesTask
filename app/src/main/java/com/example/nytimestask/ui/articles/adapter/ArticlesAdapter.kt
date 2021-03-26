package com.example.nytimestask.ui.articles.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.nytimestask.R
import com.example.nytimestask.data.model.Article
import com.example.nytimestask.ui.articles.view.ArticlesFragmentDirections
import com.squareup.picasso.Picasso

class ArticlesAdapter(private val articles:List<Article>) :
    RecyclerView.Adapter<ArticlesAdapter.ArticleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)=
        (ArticleViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.article_item_layout, parent, false)
        ))

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.articleTitle.text = articles[position].title
        holder.articleSource.text = articles[position].source
        holder.articleByLine.text = articles[position].byline
        holder.articleDate.text = articles[position].publishedDate
        if(articles[position].media.size != 0 ){
            Picasso.get()
                .load(articles[position].media[0].mediaMetadata[1].url)
                .into(holder.articleImg)
        }
        holder.itemView.setOnClickListener {
            val action = ArticlesFragmentDirections
                .actionArticlesFragmentToDetailsFragment(articles[position].id)
            it.findNavController().navigate(action)
        }
    }

    override fun getItemCount() = articles.size


    class ArticleViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val articleImg: ImageView = view.findViewById(R.id.articleImageView)
        val articleTitle: TextView = view.findViewById(R.id.articleTitleTv)
        val articleSource: TextView = view.findViewById(R.id.articleSourceTv)
        val articleByLine : TextView = view.findViewById(R.id.articleByLineTv)
        val articleDate: TextView = view.findViewById(R.id.articleDateTv)
    }
}