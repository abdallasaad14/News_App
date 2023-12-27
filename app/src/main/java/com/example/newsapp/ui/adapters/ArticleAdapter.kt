package com.example.newsapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.adapters.AdapterViewBindingAdapter.OnItemSelectedComponentListener
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentSearchNewsBinding
import com.example.newsapp.databinding.ItemArticlePreviewBinding
import com.example.newsapp.ui.adapters.ArticleAdapter.MyViewHolder
import com.example.newsapp.ui.dataClasses.Article
import com.squareup.picasso.Picasso

class ArticleAdapter() : RecyclerView.Adapter<MyViewHolder>() {
    var list = listOf<Article>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class MyViewHolder(val binding: ItemArticlePreviewBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemArticlePreviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        )
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val article = list[position]
        holder.binding.apply {
            Picasso.get().load(article.urlToImage).into(ivArticleImage)
            tvSource.text = article.source.name
            tvTitle.text = article.title
            tvDescription.text = article.description
            tvPublishedAt.text = article.publishedAt
        }
        holder.itemView.setOnClickListener {
            onItemClickListener?.let { it(article) }
        }
    }

    override fun getItemCount(): Int = list.size

    private var onItemClickListener: ((Article) -> Unit)? = null
    fun setItemOnClickListener(listener: (Article) -> Unit) {
        onItemClickListener = listener
    }

}
