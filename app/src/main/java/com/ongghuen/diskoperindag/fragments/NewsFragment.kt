package com.ongghuen.diskoperindag.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.ongghuen.diskoperindag.adapters.NewsAdapter
import com.ongghuen.diskoperindag.databinding.FragmentNewsBinding
import com.ongghuen.diskoperindag.model.News
import com.ongghuen.diskoperindag.viewmodel.NewsViewModel

class NewsFragment : Fragment() {

    private val newsViewModel: NewsViewModel by activityViewModels()
    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val isItNullHuh: List<News> = listOf(News(1, "wakeup.img", "Eden", "Wake Up", "ok i guesss"))
        newsViewModel.news.observe(viewLifecycleOwner) { news ->
            binding.newsRecyclerView.adapter = NewsAdapter(newsViewModel.news.value ?: isItNullHuh)
            binding.newsRecyclerView.setHasFixedSize(true)
        }

        binding.swipeRefresh.setOnRefreshListener {
            newsViewModel.getNews()
            binding.swipeRefresh.setRefreshing(false)
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}