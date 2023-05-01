package com.ongghuen.diskoperindag.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.ongghuen.diskoperindag.R
import com.ongghuen.diskoperindag.fragments.NewsDetailFragmentDirections
import com.ongghuen.diskoperindag.fragments.NewsFragmentDirections
import com.ongghuen.diskoperindag.model.News

class NewsAdapter(private val context: Context?, private val data: List<News>) :

    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    class NewsViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val teksviyuw: TextView = view.findViewById(R.id.title)
        val cardContainer: MaterialCardView = view.findViewById(R.id.cardContainer)
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
        holder.cardContainer.setOnClickListener {
            val navController = holder.view.findNavController()
            val toDetail = NewsFragmentDirections.actionNewsFragmentToNewsDetailFragment(kontol = context!!.getString(item.stringRes))
            navController.navigate(toDetail)
        }
    }

}