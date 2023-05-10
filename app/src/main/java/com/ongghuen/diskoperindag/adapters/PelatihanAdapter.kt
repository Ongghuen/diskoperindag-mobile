package com.ongghuen.diskoperindag.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ongghuen.diskoperindag.R
import com.ongghuen.diskoperindag.fragments.fasilitasi.FasilitasiPelatihanFragmentDirections
import com.ongghuen.diskoperindag.model.Pelatihan

class PelatihanAdapter(private val data: List<Pelatihan>) :
    ListAdapter<Pelatihan, PelatihanAdapter.PelatihanViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<Pelatihan>() {
        override fun areItemsTheSame(oldItem: Pelatihan, newItem: Pelatihan): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Pelatihan, newItem: Pelatihan): Boolean {
            return oldItem.nama == newItem.nama
        }
    }

    class PelatihanViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val namaPelatihan: TextView = view.findViewById(R.id.namaPelatihan)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PelatihanViewHolder {
        val adapterLayout =
            LayoutInflater.from(parent.context).inflate(R.layout.pelatihan_list, parent, false)
        return PelatihanViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: PelatihanViewHolder, position: Int) {
        val item = data[position]

        holder.namaPelatihan.text = item.nama
        holder.namaPelatihan.setOnClickListener {
            val toDetail =
                FasilitasiPelatihanFragmentDirections.actionFasilitasiPelatihanFragmentToFasilitasiPelatihanDetailFragment(
                    nama = item.nama,
                    penyelenggara = item.penyelenggara,
                    tanggalPelaksanaan = item.tanggal_pelaksanaan,
                    tempat = item.tempat
                )
            holder.view.findNavController().navigate(toDetail)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
