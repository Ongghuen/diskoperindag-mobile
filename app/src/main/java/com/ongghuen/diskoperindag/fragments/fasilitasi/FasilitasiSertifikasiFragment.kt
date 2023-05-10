package com.ongghuen.diskoperindag.fragments.fasilitasi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ongghuen.diskoperindag.R
import com.ongghuen.diskoperindag.adapters.SertifikasiAdapter
import com.ongghuen.diskoperindag.databinding.FragmentFasilitasiSertifikasiBinding
import com.ongghuen.diskoperindag.viewmodel.FasilitasiViewModel

class FasilitasiSertifikasiFragment : Fragment() {

    private val fasilitasiViewModel: FasilitasiViewModel by activityViewModels()
    private var _binding: FragmentFasilitasiSertifikasiBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFasilitasiSertifikasiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fasilitasiViewModel.sertifikasi.observe(viewLifecycleOwner) { sertifikasi ->
            binding.rvSertifikasi.adapter = SertifikasiAdapter(sertifikasi)
            binding.rvSertifikasi.layoutManager = LinearLayoutManager(requireContext())
            binding.rvSertifikasi.setHasFixedSize(true)
        }
    }
}