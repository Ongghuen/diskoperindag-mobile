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

// ini adalah contoh adapter
// adapter ini berguna untuk mengisi data (fill) ke recyclerview
class BantuanAdapter(private val data: List<Bantuan>) :
    ListAdapter<Bantuan, BantuanAdapter.BantuanViewHolder>(DiffCallback) {

    // ini adalah contoh penggunaan diffutils
    // digunakan untuk sebagai algoritma penggunaan list
    companion object DiffCallback : DiffUtil.ItemCallback<Bantuan>() {
        override fun areItemsTheSame(oldItem: Bantuan, newItem: Bantuan): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Bantuan, newItem: Bantuan): Boolean {
            return oldItem.nama_bantuan == newItem.nama_bantuan
        }
    }

    // viewholder adapter bantuan
    // seperti nama fungsi ini, viewholder digunakan literally untuk
    // memegang view yang ada di list context bantuan ini
    class BantuanViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val container: View = view.findViewById(R.id.container)
        val namaBantuan: TextView = view.findViewById(R.id.fasilitasiName)
        val koordinator: TextView = view.findViewById(R.id.nama_koordinator)
        val jadwal: TextView = view.findViewById(R.id.date)
    }
    // oncreate viewholder digunakan untuk mengetahui konteks mana
    // adapter ini akan menggunakan list xml yang mana
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BantuanViewHolder {
        val adapterLayout =
            LayoutInflater.from(parent.context).inflate(R.layout.bantuan_list, parent, false)
        return BantuanViewHolder(adapterLayout)
    }

    // onbindviewholder digunakan untuk mengisi value ke view yang telah dipegang
    // yang dimaksud ini adalah view yang berada di viewholder
    override fun onBindViewHolder(holder: BantuanViewHolder, position: Int) {
        val item = data[position]

        holder.namaBantuan.text = item.nama_bantuan
        holder.koordinator.text = item.koordinator
        holder.jadwal.text = item.tahun_pemberian
        holder.container.setOnClickListener {
            val navController = holder.view.findNavController()
            navController.navigate(FasilitasiBantuanFragmentDirections.actionFasilitasiBantuanFragmentToFasilitasiBantuanDetailFragment(
                item.id
            ))
        }
    }

    // seperti arti nama dari fungsi ini,
    // getitemcount digunakan untuk mengambil count item dari data yang berada
    // di parameter adapter
    override fun getItemCount(): Int {
        return data.size
    }
}