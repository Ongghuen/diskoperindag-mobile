package com.example.diskoperindag.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.diskoperindag.adapter.NewsAdapter
import com.example.diskoperindag.data.Datasource
import com.example.diskoperindag.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        val data = Datasource().loadAffirmation()
        binding.newsRecyclerView.adapter = NewsAdapter(this, data)
        binding.newsRecyclerView.setHasFixedSize(true)

    }

}

