package com.ongghuen.diskoperindag.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.ongghuen.diskoperindag.R
import com.ongghuen.diskoperindag.R.*
import com.ongghuen.diskoperindag.adapter.NewsAdapter
import com.ongghuen.diskoperindag.data.Datasource
import com.ongghuen.diskoperindag.databinding.FragmentDashboardBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ongghuen.diskoperindag.viewmodel.UserViewModel

class DashboardFragment : Fragment() {

    private val userViewModel: UserViewModel by activityViewModels()
    private var _binding: FragmentDashboardBinding? = null
    private lateinit var navController: NavController
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)

        val nestedNavHostFragment =
            childFragmentManager.findFragmentById(R.id.fg_container) as? NavHostFragment
        navController = nestedNavHostFragment?.navController!!
        binding.bottomNavigation.setupWithNavController(navController)

        userViewModel.isLoggedIn.observe(viewLifecycleOwner) { isLoggedIn ->
            if (!isLoggedIn) {
                val toLogin = DashboardFragmentDirections.actionDashboardFragmentToLoginFragment()
                findNavController().navigate(toLogin)
            }
        }

        return binding.root
    }

}

