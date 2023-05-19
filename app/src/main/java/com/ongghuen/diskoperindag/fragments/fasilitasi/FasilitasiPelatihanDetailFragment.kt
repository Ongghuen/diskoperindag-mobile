package com.ongghuen.diskoperindag.fragments.fasilitasi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ongghuen.diskoperindag.R
import com.ongghuen.diskoperindag.databinding.FragmentFasilitasiPelatihanDetailBinding

class FasilitasiPelatihanDetailFragment : Fragment() {

    private var _binding: FragmentFasilitasiPelatihanDetailBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFasilitasiPelatihanDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            binding.namaPelatihan.text = it.getString("nama")
            binding.namaLengkapPelatihan.text = it.getString("nama")
            binding.tempat.text = it.getString("tempat")
            binding.detailTempat.text = it.getString("tempat")
            binding.tanggalPelaksanaan.text = it.getString("tanggal_pelaksanaan")
            binding.detailPenyelenggara.text = it.getString("penyelenggara")
        }
    }
}