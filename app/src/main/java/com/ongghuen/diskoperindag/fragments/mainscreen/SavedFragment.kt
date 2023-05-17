package com.ongghuen.diskoperindag.fragments.mainscreen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.ongghuen.diskoperindag.R
import com.ongghuen.diskoperindag.adapters.NewsAdapter
import com.ongghuen.diskoperindag.databinding.FragmentSavedBinding
import com.ongghuen.diskoperindag.model.News
import com.ongghuen.diskoperindag.viewmodel.NewsViewModel
import com.ongghuen.diskoperindag.viewmodel.UserViewModel

class SavedFragment : Fragment() {

    private val newsViewModel: NewsViewModel by activityViewModels()
    private var _binding: FragmentSavedBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSavedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        newsViewModel.favorites.observe(viewLifecycleOwner) { favorites ->
            binding.savedRecyclerView.adapter = NewsAdapter(newsViewModel.favorites.value!!, R.layout.news_list_vertical)
            binding.savedRecyclerView.setHasFixedSize(true)
            binding.searchField.addTextChangedListener {
                val value = newsViewModel.favorites.value!!.filter { it.judul.contains(binding.searchField.text.toString(), true) }
                binding.savedRecyclerView.adapter = NewsAdapter(value, R.layout.news_list_vertical)
            }
        }

        binding.swipeRefresh.setOnRefreshListener {
            newsViewModel.getFavorite()
            binding.swipeRefresh.setRefreshing(false)
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}