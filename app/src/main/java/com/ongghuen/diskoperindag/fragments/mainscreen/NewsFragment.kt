package com.ongghuen.diskoperindag.fragments.mainscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.ongghuen.checkUrlImg
import com.ongghuen.diskoperindag.NavContentDirections
import com.ongghuen.diskoperindag.R
import com.ongghuen.diskoperindag.adapters.NewsAdapter
import com.ongghuen.diskoperindag.databinding.FragmentNewsBinding
import com.ongghuen.diskoperindag.viewmodel.NewsLoading
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

        newsViewModel.status.observe(viewLifecycleOwner) { status ->
            when(status) {
                NewsLoading.LOADING -> {
                    binding.containerNetwork.visibility = View.VISIBLE
                    binding.networkStatusImage.setImageResource(R.drawable.loading_animation)
                    binding.retry.visibility = View.GONE
                }
                NewsLoading.ERROR -> {
                    binding.containerNetwork.visibility = View.VISIBLE
                    binding.networkStatusImage.setImageResource(R.drawable.ic_connection_error)
                    binding.retry.visibility = View.VISIBLE
                }
                else -> {
                    binding.containerNetwork.visibility = View.GONE
                }
            }
        }

        newsViewModel.news.observe(viewLifecycleOwner) { news ->
            val item = news[news.size - 1]
            binding.spotlight.load(checkUrlImg(item)) {
                placeholder(R.drawable.loading_animation)
                error(R.drawable.ic_broken_image)
            }
            binding.tvHeadline.text = item.judul
            binding.spotlight.setOnClickListener {
                val toDetail = NavContentDirections.actionGlobalNewsDetailFragment(
                    id = item.id,
                    image = checkUrlImg(item),
                    title = item.judul,
                )
                findNavController().navigate(toDetail)
            }
            if (newsViewModel.news.value!!.size > 5) {
                val listRecycle =
                    newsViewModel.news.value!!.sortedByDescending { it.id }.take(5).asReversed()
                binding.newsRecyclerView.adapter =
                    NewsAdapter(listRecycle, R.layout.news_list_horizontal)
            } else {
                binding.newsRecyclerView.adapter =
                    NewsAdapter(newsViewModel.news.value!!, R.layout.news_list_horizontal)
            }
            binding.newsRecyclerView.setHasFixedSize(true)
            val layoutManager = LinearLayoutManager(requireContext())
            layoutManager.orientation = LinearLayoutManager.HORIZONTAL

            binding.newsRecyclerView.layoutManager = layoutManager
        }

        binding.swipeRefresh.setOnRefreshListener {
            newsViewModel.getNews()
            binding.swipeRefresh.setRefreshing(false)
        }

        binding.retry.setOnClickListener {
            newsViewModel.getNews()
            newsViewModel.getFavorite()
        }

        binding.tvMore.setOnClickListener {
            val toAll = NewsFragmentDirections.actionNewsFragmentToNewsAllFragment()
            findNavController().navigate(toAll)
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}