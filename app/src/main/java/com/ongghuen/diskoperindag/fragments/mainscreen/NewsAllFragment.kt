package com.ongghuen.diskoperindag.fragments.mainscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ongghuen.diskoperindag.R
import com.ongghuen.diskoperindag.adapters.NewsAdapter
import com.ongghuen.diskoperindag.databinding.FragmentNewsAllBinding
import com.ongghuen.diskoperindag.viewmodel.NewsViewModel

class NewsAllFragment : Fragment() {

    private val newsViewModel: NewsViewModel by activityViewModels()
    private var _binding: FragmentNewsAllBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewsAllBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newsViewModel.news.observe(viewLifecycleOwner) { news ->
            binding.newsRecyclerView.adapter =
                NewsAdapter(newsViewModel.news.value!!, R.layout.news_list_vertical)
            binding.newsRecyclerView.setHasFixedSize(true)
        }

        binding.swipeRefresh.setOnRefreshListener {
            newsViewModel.getNews()
            binding.swipeRefresh.setRefreshing(false)
        }
    }

}