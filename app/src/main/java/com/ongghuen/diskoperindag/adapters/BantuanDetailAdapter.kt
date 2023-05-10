package com.ongghuen.diskoperindag.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ongghuen.diskoperindag.R
import com.ongghuen.diskoperindag.model.BantuanDetail

class BantuanDetailAdapter(private val data: List<BantuanDetail.ItemBantuan>) :
    ListAdapter<BantuanDetail.ItemBantuan, BantuanDetailAdapter.BantuanDetailViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<BantuanDetail.ItemBantuan>() {
        override fun areItemsTheSame(oldItem: BantuanDetail.ItemBantuan, newItem: BantuanDetail.ItemBantuan): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: BantuanDetail.ItemBantuan, newItem: BantuanDetail.ItemBantuan): Boolean {
            return oldItem.nama_item == newItem.nama_item
        }
    }

    class BantuanDetailViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val namaItem: TextView = view.findViewById(R.id.namaItem)
        val kuantitasItem: TextView = view.findViewById(R.id.kuantitasItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BantuanDetailViewHolder {
        val adapterLayout =
            LayoutInflater.from(parent.context).inflate(R.layout.bantuan_detail_list, parent, false)
        return BantuanDetailViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: BantuanDetailViewHolder, position: Int) {
        val item = data[position]

        holder.namaItem.text = item.nama_item
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
