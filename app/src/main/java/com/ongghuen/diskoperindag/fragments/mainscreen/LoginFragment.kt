package com.ongghuen.diskoperindag.fragments.mainscreen

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
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

        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("CEPTION FIREBASE", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }
            Log.d("CEPTION FIREBASE", task.result)
        })

        viewModel.isLoggedIn.observe(viewLifecycleOwner) { isLoggedIn ->
            if (isLoggedIn) {
                val toDashboard = LoginFragmentDirections.actionLoginFragmentToDashboardFragment()
                binding.root.findNavController().navigate(toDashboard)
            }
        }

        viewModel.status.observe(viewLifecycleOwner) { status ->
            when (status) {
                UserLoading.LOADING -> {
                    binding.clStatus.visibility = View.VISIBLE
                    binding.clStatus.setBackgroundColor(Color.parseColor("#FFA500"))
                }

                UserLoading.WRONG_PASSWORD -> {
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

                else -> binding.clStatus.visibility = View.GONE
            }
        }

        binding.btnLogin.setOnClickListener {
            viewModel.login(
                binding.fieldEmail.text.toString(), binding.fieldPassword.text.toString()
            )
        }

        binding.btnRegister.setOnClickListener {
            val toRegister = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "0823-3046-3756"))
            startActivity(toRegister)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}