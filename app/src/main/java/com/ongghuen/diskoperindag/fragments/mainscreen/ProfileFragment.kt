package com.ongghuen.diskoperindag.fragments.mainscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.ongghuen.diskoperindag.R
import com.ongghuen.diskoperindag.databinding.FragmentProfileBinding
import com.ongghuen.diskoperindag.viewmodel.FasilitasiViewModel
import com.ongghuen.diskoperindag.viewmodel.NewsViewModel
import com.ongghuen.diskoperindag.viewmodel.UserLoading
import com.ongghuen.diskoperindag.viewmodel.UserViewModel

class ProfileFragment : Fragment() {

    private val userViewModel: UserViewModel by activityViewModels()
    private val fasilitasiViewModel: FasilitasiViewModel by activityViewModels()
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userViewModel.status.observe(viewLifecycleOwner) { status ->
            when (status) {
                UserLoading.LOADING -> {
                    binding.containerStatus.visibility = View.VISIBLE
                    binding.networkStatus.setImageResource(R.drawable.loading_animation)
                }

                UserLoading.ERROR -> {
                    binding.containerStatus.visibility = View.VISIBLE
                    binding.networkStatus.setImageResource(R.drawable.ic_connection_error)
                }

                else -> {
                    binding.containerStatus.visibility = View.GONE
                }
            }
        }

        userViewModel.currentUser.observe(viewLifecycleOwner) { user ->
            binding.name.text = user.user!!.name
            binding.clAccountSetting.setOnClickListener {
                val toDetail =
                    ProfileFragmentDirections.actionProfileFragmentToProfileDetailFragment()
                findNavController().navigate(toDetail)
            }
        }

        fasilitasiViewModel.bantuan.observe(viewLifecycleOwner) { bantuan ->
            binding.countBantuan.text = bantuan.size.toString()
            binding.bantuanDetail.setOnClickListener {
                val toDetail =
                    ProfileFragmentDirections.actionProfileFragmentToFasilitasiBantuanFragment()
                findNavController().navigate(toDetail)
            }
        }

        fasilitasiViewModel.sertifikasi.observe(viewLifecycleOwner) { sertifikasi ->
            binding.countSertifikat.text = sertifikasi.size.toString()
            binding.sertifikasiDetail.setOnClickListener {
                val toDetail =
                    ProfileFragmentDirections.actionProfileFragmentToFasilitasiSertifikasiFragment()
                findNavController().navigate(toDetail)
            }
        }

        fasilitasiViewModel.pelatihan.observe(viewLifecycleOwner) { pelatihan ->
            binding.countPelatihan.text = pelatihan.size.toString()
            binding.pelatihanDetail.setOnClickListener {
                val toDetail =
                    ProfileFragmentDirections.actionProfileFragmentToFasilitasiPelatihanFragment()
                findNavController().navigate(toDetail)
            }
        }

    }

}