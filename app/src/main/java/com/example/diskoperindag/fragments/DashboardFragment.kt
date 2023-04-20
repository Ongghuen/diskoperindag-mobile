package com.example.diskoperindag.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.diskoperindag.R
import com.example.diskoperindag.R.*
import com.example.diskoperindag.adapter.NewsAdapter
import com.example.diskoperindag.data.Datasource
import com.example.diskoperindag.databinding.FragmentDashboardBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private lateinit var navController: NavController
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val nestedNavHostFragment = childFragmentManager.findFragmentById(R.id.fg_container) as? NavHostFragment
        navController = nestedNavHostFragment?.navController!!
        binding.bottomNavigation.setupWithNavController(navController)

        return binding.root
    }

}

