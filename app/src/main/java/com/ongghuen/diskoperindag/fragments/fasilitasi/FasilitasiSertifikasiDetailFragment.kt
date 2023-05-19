package com.ongghuen.diskoperindag.fragments.fasilitasi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ongghuen.diskoperindag.R
import com.ongghuen.diskoperindag.databinding.FragmentFasilitasiSertifikasiDetailBinding

class FasilitasiSertifikasiDetailFragment : Fragment() {

    private var _binding: FragmentFasilitasiSertifikasiDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFasilitasiSertifikasiDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            binding.noSertifikasi.text = it.getString("no_sertifikat")
            binding.namaSertifikat.text = it.getString("nama")
            binding.namaLengkapSertifikat.text = it.getString("nama")
            binding.tanggalTerbit.text = it.getString("tanggal_terbit")
            binding.keterangan.text = it.getString("keterangan")
        }
    }
}