package com.example.diskoperindag.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.diskoperindag.R
import com.example.diskoperindag.model.News

class NewsAdapter(private val context: Context?, private val data: List<News>) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    class NewsViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val teksviyuw: TextView = view.findViewById<TextView>(R.id.item_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val adapterLayout =
            LayoutInflater.from(parent.context).inflate(R.layout.news_list, parent, false)

        return NewsViewHolder(adapterLayout)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val item = data[position]

        holder.teksviyuw.text = context!!.getString(item.stringRes)
    }

}