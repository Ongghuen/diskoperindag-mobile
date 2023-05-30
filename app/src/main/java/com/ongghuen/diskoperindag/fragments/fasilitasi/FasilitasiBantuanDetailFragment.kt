package com.ongghuen.diskoperindag.fragments.fasilitasi

import android.icu.lang.UCharacter.VerticalOrientation
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ongghuen.diskoperindag.R
import com.ongghuen.diskoperindag.adapters.BantuanDetailAdapter
import com.ongghuen.diskoperindag.databinding.FragmentFasilitasiBantuanDetailBinding
import com.ongghuen.diskoperindag.viewmodel.FasilitasiLoading
import com.ongghuen.diskoperindag.viewmodel.FasilitasiViewModel

class FasilitasiBantuanDetailFragment : Fragment() {

    private val fasilitasiViewModel: FasilitasiViewModel by activityViewModels()
    private var _binding: FragmentFasilitasiBantuanDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFasilitasiBantuanDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fasilitasiViewModel.getBantuanDetail(arguments?.getInt("id").toString())

        fasilitasiViewModel.status.observe(viewLifecycleOwner) { status ->
            when(status) {
                FasilitasiLoading.LOADING -> {
                    binding.containerNetwork.visibility = View.VISIBLE
                    binding.networkStatusImage.setImageResource(R.drawable.loading_animation)
                }
                else -> {
                    binding.containerNetwork.visibility = View.GONE
                }
            }

        }

        fasilitasiViewModel.bantuanDetail.observe(viewLifecycleOwner) { detail ->
            binding.namaBantuan.text = detail.nama_bantuan
            binding.rvItemBantuan.adapter = BantuanDetailAdapter(detail.item_bantuan)
            binding.rvItemBantuan.layoutManager = LinearLayoutManager(requireContext())
            binding.rvItemBantuan.setHasFixedSize(true)

            binding.date.text = detail.tahun_pemberian
            binding.namaKoordinator.text = detail.koordinator
            binding.namaSumberAnggaran.text = detail.sumber_anggaran
        }
    }
}