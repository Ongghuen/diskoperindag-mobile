package com.ongghuen.diskoperindag.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import com.ongghuen.diskoperindag.R
import com.ongghuen.diskoperindag.databinding.FragmentAuthBinding
import com.ongghuen.diskoperindag.viewmodel.UserViewModel

class AuthFragment : Fragment() {

    private var _binding: FragmentAuthBinding? = null
    private val binding get() = _binding!!
    private var direction: NavDirections? = null

    private val viewModel: UserViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAuthBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.isLoggedIn.observe(viewLifecycleOwner) { isLoggedIn ->
            if (isLoggedIn) {
                direction = AuthFragmentDirections.actionAuthFragmentToDashboardFragment()
            } else {
                direction = AuthFragmentDirections.actionAuthFragmentToLoginFragment()
            }
            binding.root.findNavController().navigate(direction!!)
        }

    }
}