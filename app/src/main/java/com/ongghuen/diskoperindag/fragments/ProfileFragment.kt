package com.ongghuen.diskoperindag.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
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
        viewModel.currentUser.observe(viewLifecycleOwner, { user ->
            binding.tvProfile.text = "My name is ${user.user?.name} and my token is ${user.token}"
        })
    }

}