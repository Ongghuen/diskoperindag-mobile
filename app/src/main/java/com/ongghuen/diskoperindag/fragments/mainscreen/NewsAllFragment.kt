package com.ongghuen.diskoperindag.fragments.mainscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ongghuen.diskoperindag.R
import com.ongghuen.diskoperindag.adapters.NewsAdapter
import com.ongghuen.diskoperindag.databinding.FragmentNewsAllBinding
import com.ongghuen.diskoperindag.viewmodel.NewsLoading
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

        newsViewModel.status.observe(viewLifecycleOwner) { status ->
            when(status) {
                NewsLoading.LOADING -> {
                    binding.containerStatus.visibility = View.VISIBLE
                    binding.networkStatus.setImageResource(R.drawable.loading_animation)
                }
                NewsLoading.ERROR -> {
                    binding.containerStatus.visibility = View.VISIBLE
                    binding.networkStatus.setImageResource(R.drawable.ic_connection_error)
                }
                else -> {
                    binding.containerStatus.visibility = View.GONE
                }
            }
        }

        newsViewModel.news.observe(viewLifecycleOwner) { news ->
            if (news.isEmpty()) {
                binding.placeholderNone.visibility = View.VISIBLE
            } else {
                binding.placeholderNone.visibility = View.GONE
            }
            binding.newsRecyclerView.adapter =
                NewsAdapter(news.reversed(), R.layout.news_list_vertical)
            binding.newsRecyclerView.setHasFixedSize(true)
            binding.searchField.addTextChangedListener {
                val value = newsViewModel.news.value!!.filter { it.judul.contains(binding.searchField.text.toString(), true) }
                binding.newsRecyclerView.adapter = NewsAdapter(value, R.layout.news_list_vertical)
            }
        }

        binding.swipeRefresh.setOnRefreshListener {
            newsViewModel.getNews()
            binding.swipeRefresh.setRefreshing(false)
        }
    }

}