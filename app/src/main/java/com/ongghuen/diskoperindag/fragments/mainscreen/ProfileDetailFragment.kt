package com.ongghuen.diskoperindag.fragments.mainscreen

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.ongghuen.diskoperindag.databinding.FragmentProfileDetailBinding
import com.ongghuen.diskoperindag.model.ChangePasswordRequest
import com.ongghuen.diskoperindag.viewmodel.ChangePassLoading
import com.ongghuen.diskoperindag.viewmodel.UserLoading
import com.ongghuen.diskoperindag.viewmodel.UserViewModel

class ProfileDetailFragment : Fragment() {

    private val viewModel: UserViewModel by activityViewModels()
    private var _binding: FragmentProfileDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProfileDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.statusPass.observe(viewLifecycleOwner) { status ->
            when (status) {
                ChangePassLoading.SUCCESS -> Toast.makeText(
                    requireContext(), "Password berhasil diganti!", Toast.LENGTH_SHORT
                ).show()

                ChangePassLoading.ERROR -> Toast.makeText(
                    requireContext(), "Password gagal diganti!", Toast.LENGTH_SHORT
                ).show()

                else -> {}
            }
        }

        viewModel.status.observe(viewLifecycleOwner) { status ->
            when(status) {
                UserLoading.LOGOUT_ERROR -> {
                    Toast.makeText(requireContext(), "Logout gagal! pastikan anda mempunyai koneksi internet", Toast.LENGTH_SHORT).show()
                }

                else -> {}
            }
        }

        viewModel.currentUser.observe(viewLifecycleOwner) { user ->
            binding.btnSubmit.setOnClickListener {
                val currentPassword = binding.fieldCurrentPassword.text.toString()
                val newPassword = binding.fieldNewPassword.text.toString()
                val newPasswordVerify = binding.fieldNewPasswordVerify.text.toString()

                Log.d(
                    "CEPTION",
                    "$currentPassword, ${user.user!!.password}, $newPassword, $newPasswordVerify"
                )

                if (newPassword.length != 0 && newPassword == newPasswordVerify) {
                    val password = ChangePasswordRequest(
                        currentPassword, newPassword
                    )
                    viewModel.changePassword(password)
                } else {
                    Toast.makeText(
                        requireContext(), "Konfirmasi Password Salah!", Toast.LENGTH_SHORT
                    ).show()
                }

            }
            binding.btnLogout.setOnClickListener {
                logoutDialog()
            }
        }
    }

    private fun logoutDialog() {
        val alertDialog = AlertDialog.Builder(requireContext())
        alertDialog.setTitle("Logout")
        alertDialog.setMessage("Anda akan keluar dari dashboard dan langsung terlempar ke layar login.")
        alertDialog.setPositiveButton("OK") { dialogInterface: DialogInterface, _: Int ->
            dialogInterface.dismiss()
            viewModel.logout()
        }
        alertDialog.setNegativeButton("Cancel") { dialogInterface: DialogInterface, _: Int ->
            dialogInterface.dismiss()
        }
        alertDialog.setCancelable(false)
        alertDialog.show()
    }
}