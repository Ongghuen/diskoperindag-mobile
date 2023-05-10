package com.ongghuen.diskoperindag.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ongghuen.diskoperindag.R
import com.ongghuen.diskoperindag.fragments.fasilitasi.FasilitasiBantuanFragmentDirections
import com.ongghuen.diskoperindag.model.Bantuan

class BantuanAdapter(private val data: List<Bantuan>) :
    ListAdapter<Bantuan, BantuanAdapter.BantuanViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<Bantuan>() {
        override fun areItemsTheSame(oldItem: Bantuan, newItem: Bantuan): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Bantuan, newItem: Bantuan): Boolean {
            return oldItem.nama_bantuan == newItem.nama_bantuan
        }
    }

    class BantuanViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val namaBantuan: TextView = view.findViewById(R.id.namaBantuan)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BantuanViewHolder {
        val adapterLayout =
            LayoutInflater.from(parent.context).inflate(R.layout.bantuan_list, parent, false)
        return BantuanViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: BantuanViewHolder, position: Int) {
        val item = data[position]

        holder.namaBantuan.text = item.nama_bantuan
        holder.namaBantuan.setOnClickListener {
            val navController = holder.view.findNavController()
            navController.navigate(FasilitasiBantuanFragmentDirections.actionFasilitasiBantuanFragmentToFasilitasiBantuanDetailFragment(
                item.id
            ))
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}