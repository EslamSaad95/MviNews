package com.example.mvinews.presentation.news

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mvinews.domain.news.NewsEntity
import com.example.mvinews.R
import com.example.mvinews.domain.news.NewsData

class NewsAdapter(private val newsList: List<NewsData>) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_news, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsAdapter.ViewHolder, position: Int) {
        val itemsViewModel = newsList[position]

        holder.tvNewsTitle.text = itemsViewModel.title
        holder.tvNewsDescription.text = itemsViewModel.description
        holder.tvNewsAuthor.text = itemsViewModel.author
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val tvNewsTitle: TextView = itemView.findViewById(R.id.tvNewsTitle)
        val tvNewsDescription: TextView = itemView.findViewById(R.id.tvNewsDescription)
        val tvNewsAuthor: TextView = itemView.findViewById(R.id.tvNewsAuthor)
    }
}




