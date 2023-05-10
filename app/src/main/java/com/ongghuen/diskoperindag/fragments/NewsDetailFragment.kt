package com.ongghuen.diskoperindag.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.map
import coil.load
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

        val checkId = newsViewModel.favorites.map { fav -> fav.map { it.id } }
        checkId.observe(viewLifecycleOwner) { id ->

            if (id.contains(arguments?.getInt("id"))) {
                binding.btnSave.text = "Unsave"
            } else {
                binding.btnSave.text = "Save"
            }

            binding.btnSave.setOnClickListener {
                val argId: Int = arguments?.getInt("id")!!
                if (!id.contains(argId)) {
                    newsViewModel.addFavorite(argId.toString())
                } else {
                    newsViewModel.deleteFavorite(argId.toString())
                }
            }

        }

        arguments?.let {
            binding.image.load(it.getString("image"))
            binding.title.text = it.getString("title")
            binding.subTitle.text = it.getString("subTitle")
            binding.body.text = it.getString("body")
        }
    }

}