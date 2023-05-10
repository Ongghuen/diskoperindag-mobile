package com.ongghuen.diskoperindag.fragments.fasilitasi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ongghuen.diskoperindag.R
import com.ongghuen.diskoperindag.adapters.PelatihanAdapter
import com.ongghuen.diskoperindag.databinding.FragmentFasilitasiBantuanBinding
import com.ongghuen.diskoperindag.databinding.FragmentFasilitasiPelatihanBinding
import com.ongghuen.diskoperindag.viewmodel.FasilitasiViewModel

class FasilitasiPelatihanFragment : Fragment() {

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

        fasilitasiViewModel.pelatihan.observe(viewLifecycleOwner) { pelatihan ->
            binding.rvPelatihan.adapter = PelatihanAdapter(pelatihan)
            binding.rvPelatihan.setHasFixedSize(true)
            binding.rvPelatihan.layoutManager = LinearLayoutManager(requireContext())

        }
    }

}