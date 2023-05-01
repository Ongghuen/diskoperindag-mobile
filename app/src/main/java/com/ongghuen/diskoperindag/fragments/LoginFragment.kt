package com.ongghuen.diskoperindag.fragments

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.ongghuen.diskoperindag.databinding.FragmentLoginBinding
import com.ongghuen.diskoperindag.viewmodel.UserLoading
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

        viewModel.isLoggedIn.observe(viewLifecycleOwner) { isLoggedIn ->
            if (isLoggedIn) {
                val toDashboard = LoginFragmentDirections.actionLoginFragmentToDashboardFragment()
                binding.root.findNavController().navigate(toDashboard)
            }
        }

        viewModel.status.observe(viewLifecycleOwner) { status ->
            when (status) {
                UserLoading.INIT -> binding.clStatus.visibility = View.GONE
                UserLoading.LOADING -> {
                    binding.clStatus.visibility = View.VISIBLE
                    binding.clStatus.setBackgroundColor(Color.parseColor("#FFA500"))
                }

                UserLoading.ERROR -> {
                    binding.clStatus.visibility = View.VISIBLE
                    binding.clStatus.setBackgroundColor(Color.parseColor("#ff0000"))
                    Toast.makeText(
                        requireContext(),
                        "Username atau Password salah!",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                UserLoading.SUCCESS -> {
                    binding.clStatus.visibility = View.VISIBLE
                    binding.clStatus.setBackgroundColor(Color.parseColor("#00ff00"))
                }

                UserLoading.FINISH -> binding.clStatus.visibility = View.GONE

                else -> binding.clStatus.visibility = View.GONE
            }
        }

        binding.btnLogin.setOnClickListener {
            viewModel.login(
                binding.fieldEmail.text.toString(), binding.fieldPassword.text.toString()
            )
        }

        binding.btnRegister.setOnClickListener {
            val toRegister = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            findNavController().navigate(toRegister)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}