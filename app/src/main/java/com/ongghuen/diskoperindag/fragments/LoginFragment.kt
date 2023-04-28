package com.ongghuen.diskoperindag.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.ongghuen.diskoperindag.R
import com.ongghuen.diskoperindag.databinding.FragmentLoginBinding
import com.ongghuen.diskoperindag.viewmodel.UserViewModel

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val viewModel: UserViewModel by activityViewModels()
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.isLoggedIn.observe(viewLifecycleOwner, { isLoggedIn ->
            if (isLoggedIn) {
                val toDashboard = LoginFragmentDirections.actionLoginFragmentToDashboardFragment()
                binding.root.findNavController().navigate(toDashboard)
            }
        })

        binding.btnLogin.setOnClickListener {
            viewModel.login(
                binding.fieldUsername.text.toString(), binding.fieldPassword.text.toString()
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}