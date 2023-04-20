package com.example.diskoperindag.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.diskoperindag.adapter.NewsAdapter
import com.example.diskoperindag.data.Datasource
import com.example.diskoperindag.databinding.FragmentNewsBinding

class NewsFragment : Fragment() {

    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data = Datasource().loadAffirmation()

        binding.newsRecyclerView.adapter = NewsAdapter(context, data)
        binding.newsRecyclerView.setHasFixedSize(true)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}