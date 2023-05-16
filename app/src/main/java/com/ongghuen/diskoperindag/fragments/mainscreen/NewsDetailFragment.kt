package com.ongghuen.diskoperindag.fragments.mainscreen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.map
import coil.load
import com.ongghuen.convertTimestamp
import com.ongghuen.diskoperindag.R
import com.ongghuen.diskoperindag.databinding.FragmentNewsDetailBinding
import com.ongghuen.diskoperindag.viewmodel.NewsViewModel

class NewsDetailFragment : Fragment() {

    private val newsViewModel: NewsViewModel by activityViewModels()
    private var _binding: FragmentNewsDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentNewsDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        newsViewModel.getNews(arguments?.getInt("id").toString())

        newsViewModel.detail.observe(viewLifecycleOwner) { detail ->
            binding.subTitle.text = detail.subjudul
            binding.body.text = detail.body
            binding.date.text = convertTimestamp(detail.created_at)
        }

        val checkId = newsViewModel.favorites.map { fav -> fav.map { it.id } }
        checkId.observe(viewLifecycleOwner) { id ->

            if (id.contains(arguments?.getInt("id"))) {
                binding.tvSave.text = "Tersimpan"
                binding.icSave.setImageResource(R.drawable.ic_tersimpan)
            } else {
                binding.tvSave.text = "Simpan"
                binding.icSave.setImageResource(R.drawable.ic_simpan)
            }

            binding.rcSave.setOnClickListener {
                val argId: Int = arguments?.getInt("id")!!
                Log.d("CEPTIONTEST", "PLER")
                if (id.contains(argId)) {
                    newsViewModel.deleteFavorite(argId.toString())
                } else {
                    newsViewModel.addFavorite(argId.toString())
                }
            }
        }


        arguments?.let {
            binding.image.load(it.getString("image")) {
                placeholder(R.drawable.loading_animation)
                error(R.drawable.ic_broken_image)
            }
            binding.title.text = it.getString("title")
            binding.subTitle.text = it.getString("subTitle")
        }
    }

}