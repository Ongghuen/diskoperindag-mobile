package com.ongghuen.diskoperindag.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.google.android.material.card.MaterialCardView
import com.ongghuen.diskoperindag.NavContentDirections
import com.ongghuen.diskoperindag.R
import com.ongghuen.diskoperindag.fragments.NewsFragmentDirections
import com.ongghuen.diskoperindag.model.News
import com.ongghuen.diskoperindag.network.BASE
import com.ongghuen.diskoperindag.network.BASE_URL
import com.ongghuen.diskoperindag.network.DiskoperindagApiService

class NewsAdapter(private val data: List<News>) :
    ListAdapter<News, NewsAdapter.NewsViewHolder>(DiffCallback) {
    companion object DiffCallback : DiffUtil.ItemCallback<News>() {
        override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem.image == oldItem.image
        }

    }

    class NewsViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val cardContainer: MaterialCardView = view.findViewById(R.id.cardContainer)
        val image: ImageView = view.findViewById(R.id.newsImage)
        val title: TextView = view.findViewById(R.id.title)
        val subTitle: TextView = view.findViewById(R.id.subTitle)
        val body: TextView = view.findViewById(R.id.body)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val adapterLayout =
            LayoutInflater.from(parent.context).inflate(R.layout.news_list, parent, false)

        return NewsViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val item = data[position]

        fun checkUrlImg(): String {
            if (item.image.contains("http", ignoreCase = true)) {
                return item.image
            } else {
                return "${BASE}/images/berita/" + item.image
            }
        }

        holder.image.load(checkUrlImg())
        holder.title.text = item.judul
        holder.subTitle.text = item.subjudul
        holder.body.text = item.body
        holder.cardContainer.setOnClickListener {
            val navController = holder.view.findNavController()
            val toDetail = NavContentDirections.actionGlobalNewsDetailFragment(
                image = checkUrlImg(),
                title = item.judul,
                subTitle = item.subjudul,
                body = item.body
            )
            navController.navigate(toDetail)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

}