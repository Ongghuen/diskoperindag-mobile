package com.ongghuen.diskoperindag.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ongghuen.diskoperindag.R
import com.ongghuen.diskoperindag.model.Sertifikasi

class SertifikasiAdapter(private val data: List<Sertifikasi>) :
    ListAdapter<Sertifikasi, SertifikasiAdapter.SertifikasiViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<Sertifikasi>() {
        override fun areItemsTheSame(oldItem: Sertifikasi, newItem: Sertifikasi): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Sertifikasi, newItem: Sertifikasi): Boolean {
            return oldItem.keterangan == newItem.keterangan
        }
    }

    class SertifikasiViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val namaSertifikasi: TextView = view.findViewById(R.id.namaSertifikasi)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SertifikasiViewHolder {
        val adapterLayout =
            LayoutInflater.from(parent.context).inflate(R.layout.sertifikasi_list, parent, false)
        return SertifikasiViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: SertifikasiViewHolder, position: Int) {
        val item = data[position]

        holder.namaSertifikasi.text = item.nama
        holder.namaSertifikasi.setOnClickListener {
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
