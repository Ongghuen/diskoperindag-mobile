package com.ongghuen.diskoperindag.fragments.fasilitasi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ongghuen.diskoperindag.adapters.PelatihanAdapter
import com.ongghuen.diskoperindag.databinding.FragmentFasilitasiPelatihanBinding
import com.ongghuen.diskoperindag.viewmodel.FasilitasiViewModel

// ini adalah contoh gambaran umum penggunaan viewmodel di fragment
class FasilitasiPelatihanFragment : Fragment() {

    // variabel viewmodel di bawah ini menggunakan delegated properties activityviewmodel
    // agar fragment lainnya seperti di detail atau yg lain tetap memiliki value yang sama
    private val fasilitasiViewModel: FasilitasiViewModel by activityViewModels()
    private var _binding: FragmentFasilitasiPelatihanBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFasilitasiPelatihanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // berikut adalah penggunaan value dari viewmodel, tiap fungsi viewmodel yang memanggil
        // retrofit (dari api) dipanggil maka yang di view juga akan ikut berubah
        fasilitasiViewModel.pelatihan.observe(viewLifecycleOwner) { pelatihan ->
            binding.rvPelatihan.adapter = PelatihanAdapter(pelatihan)
            binding.rvPelatihan.setHasFixedSize(true)
            binding.rvPelatihan.layoutManager = LinearLayoutManager(requireContext())

        }
    }

}