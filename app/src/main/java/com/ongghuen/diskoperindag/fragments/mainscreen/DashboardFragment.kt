package com.ongghuen.diskoperindag.fragments.mainscreen

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.ongghuen.diskoperindag.R
import com.ongghuen.diskoperindag.databinding.FragmentDashboardBinding
import com.ongghuen.diskoperindag.viewmodel.FasilitasiViewModel
import com.ongghuen.diskoperindag.viewmodel.NewsViewModel
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

        val prefs = activity?.getSharedPreferences("diskoperindag", Context.MODE_PRIVATE)
        val isLoggedIn = prefs!!.getBoolean("IsLoggedIn", false)
        Log.d("VALUECHECK", isLoggedIn.toString())

        binding.apply {
            toolbar.setupWithNavController(navController)
        }

        setupSmoothBottomBar()


        userViewModel.isLoggedIn.observe(viewLifecycleOwner) { isLoggedIn ->
            if (!isLoggedIn) {
                val toLogin = DashboardFragmentDirections.actionDashboardFragmentToLoginFragment()
                findNavController().navigate(toLogin)
            }
        }

        return binding.root
    }

    private fun setupSmoothBottomBar() {
        var popupMenu: PopupMenu = PopupMenu(requireContext(), null)
        popupMenu.inflate(R.menu.bottom_nav_menu)
        var menu: Menu = popupMenu.menu
        binding.bottomNavigation.setupWithNavController(menu, navController)
    }

}

