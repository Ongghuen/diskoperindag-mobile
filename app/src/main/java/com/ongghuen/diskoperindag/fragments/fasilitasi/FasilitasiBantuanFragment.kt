package com.ongghuen.diskoperindag.fragments.fasilitasi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.ongghuen.diskoperindag.R
import com.ongghuen.diskoperindag.adapters.BantuanAdapter
import com.ongghuen.diskoperindag.databinding.FragmentFasilitasiBantuanBinding
import com.ongghuen.diskoperindag.viewmodel.FasilitasiViewModel

class FasilitasiBantuanFragment : Fragment() {

    private val fasilitasiViewModel: FasilitasiViewModel by activityViewModels()
    private var _binding: FragmentFasilitasiBantuanBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFasilitasiBantuanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fasilitasiViewModel.bantuan.observe(viewLifecycleOwner) { bantuan ->
            binding.rvBantuan.adapter = BantuanAdapter(bantuan.reversed())
            binding.rvBantuan.setHasFixedSize(true)

            binding.swipeRefresh.setOnRefreshListener {
                fasilitasiViewModel.getBantuan()
                binding.swipeRefresh.isRefreshing = false
            }
        }
    }
}