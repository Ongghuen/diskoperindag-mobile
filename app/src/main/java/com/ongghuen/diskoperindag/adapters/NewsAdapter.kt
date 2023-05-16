package com.ongghuen.diskoperindag.adapters

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
import com.ongghuen.checkUrlImg
import com.ongghuen.diskoperindag.NavContentDirections
import com.ongghuen.diskoperindag.R
import com.ongghuen.diskoperindag.model.News

class NewsAdapter(private val data: List<News>, private val layout: Int) :
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
        val container: View  = view.findViewById(R.id.container)
        val image: ImageView = view.findViewById(R.id.newsImage)
        val title: TextView = view.findViewById(R.id.title)
        val date: TextView = view.findViewById(R.id.date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val adapterLayout =
            LayoutInflater.from(parent.context).inflate(layout, parent, false)

        return NewsViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val item = data[position]

        holder.image.load(checkUrlImg(item)){
            placeholder(R.drawable.loading_animation)
            error(R.drawable.ic_broken_image)
        }
        holder.title.text = item.judul
        holder.date.text = item.created_at
        holder.container.setOnClickListener {
            val navController = holder.view.findNavController()
            val toDetail = NavContentDirections.actionGlobalNewsDetailFragment(
                id = item.id,
                image = checkUrlImg(item),
                title = item.judul,
            )
            navController.navigate(toDetail)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

}