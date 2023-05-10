package com.ongghuen.diskoperindag.fragments.mainscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.ongghuen.diskoperindag.databinding.FragmentProfileBinding
import com.ongghuen.diskoperindag.viewmodel.UserViewModel

class ProfileFragment : Fragment() {

    private val viewModel: UserViewModel by activityViewModels()
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

        viewModel.currentUser.observe(viewLifecycleOwner) { user ->
            binding.tvProfile.text = "My name is ${user!!.user?.name} and my token is ${user.token}"
        }

        binding.btnProfile.setOnClickListener {
            findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToProfileDetailFragment())
        }

        binding.btnBantuan.setOnClickListener {
            findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToFasilitasiBantuanFragment())
        }

        binding.btnSertifikasi.setOnClickListener {
            findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToFasilitasiSertifikasiFragment())
        }

        binding.btnPelatihan.setOnClickListener {
            findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToFasilitasiPelatihanFragment())
        }

        binding.btnSurat.setOnClickListener {
            findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToSuratFragment())
        }

        binding.btnLogout.setOnClickListener {
            viewModel.logout()
        }
    }

}