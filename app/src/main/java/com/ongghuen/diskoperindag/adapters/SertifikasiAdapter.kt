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
import com.ongghuen.diskoperindag.fragments.fasilitasi.FasilitasiSertifikasiFragmentDirections
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
        val container: View = view.findViewById(R.id.container)

        val namaSertifikasi: TextView = view.findViewById(R.id.fasilitasiName)
        val noSertifikasi: TextView = view.findViewById(R.id.nomorSertifikat)
        val tanggalTerbit: TextView = view.findViewById(R.id.detail_tanggal_terbit)
        val tanggalKadaluarsa: TextView = view.findViewById(R.id.detail_tanggal_kadaluarsa)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SertifikasiViewHolder {
        val adapterLayout =
            LayoutInflater.from(parent.context).inflate(R.layout.sertifikasi_list, parent, false)
        return SertifikasiViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: SertifikasiViewHolder, position: Int) {
        val item = data[position]

        holder.namaSertifikasi.text = item.nama
        holder.noSertifikasi.text = item.no_sertifikat
        holder.tanggalTerbit.text = item.tanggal_terbit
        holder.tanggalKadaluarsa.text = item.kadaluarsa_penyelenggara

        holder.container.setOnClickListener {
            val toDetail = FasilitasiSertifikasiFragmentDirections.actionFasilitasiSertifikasiFragmentToFasilitasiSertifikasiDetailFragment(
                noSertifikat = item.no_sertifikat,
                nama = item.nama,
                tanggalTerbit = item.tanggal_terbit,
                kadaluarsaPenyelenggara = item.kadaluarsa_penyelenggara,
                keterangan = item.keterangan
            )
            holder.view.findNavController().navigate(toDetail)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
