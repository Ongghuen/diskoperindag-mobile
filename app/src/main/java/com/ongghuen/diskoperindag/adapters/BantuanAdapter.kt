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
        val container: View = view.findViewById(R.id.container)
        val namaBantuan: TextView = view.findViewById(R.id.fasilitasiName)
        val jenis_usaha: TextView = view.findViewById(R.id.nama_jenis_usaha)
        val koordinator: TextView = view.findViewById(R.id.nama_koordinator)
        val jadwal: TextView = view.findViewById(R.id.date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BantuanViewHolder {
        val adapterLayout =
            LayoutInflater.from(parent.context).inflate(R.layout.bantuan_list, parent, false)
        return BantuanViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: BantuanViewHolder, position: Int) {
        val item = data[position]

        holder.namaBantuan.text = item.nama_bantuan
        holder.jenis_usaha.text = item.jenis_usaha
        holder.koordinator.text = item.koordinator
        holder.jadwal.text = item.tahun_pemberian
        holder.container.setOnClickListener {
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